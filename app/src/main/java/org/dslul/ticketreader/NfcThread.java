package org.dslul.ticketreader;

import static org.dslul.ticketreader.util.HelperFunctions.hexStringToByteArray;

import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcA;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//parts of code from http://www.emutag.com/soft.php

public class NfcThread extends Thread {

    private final Context context;
    private final Intent intent;
    private final Handler mTextBufferHandler;
    private final Handler mToastShortHandler;
    private final Handler mToastLongHandler;
    private final Handler mShowInfoDialogHandler;

    private final byte[] readBuffer = new byte[1024]; // maximum theoretical capacity of MIFARE Ultralight

    NfcThread(
            Context context,
            Intent intent,
            Handler mTextBufferHandler, Handler mToastShortHandler, Handler mToastLongHandler, Handler mShowInfoDialogHandler
            ) {
        this.context = context;
        this.intent = intent;
        this.mTextBufferHandler = mTextBufferHandler;
        this.mToastShortHandler = mToastShortHandler;
        this.mToastLongHandler = mToastLongHandler;
        this.mShowInfoDialogHandler = mShowInfoDialogHandler;
    }

    public void run() {
        final Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        //check type of nfc tag and handle it accordingly
        if(tagFromIntent.getTechList()[0].equals(IsoDep.class.getName())) {
            handleIsoDep(tagFromIntent);
        } else {
            handleNfcA(tagFromIntent);
        }
    }


    private void handleIsoDep(Tag tagFromIntent) {
        IsoDep isoDep = IsoDep.get(tagFromIntent);
        if (isoDep != null) {
            try {
                isoDep.connect();

                List<byte[]> dumplist = new ArrayList<>();

                //selectApplication - dumplist index 0
                dumplist.add(isoDep.transceive(hexStringToByteArray("00A404000E315449432E494341D38012009101")));
                //efEnvironment - dumplist index 1
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B2013C1D")));
                //efContractList - dumplist index 2
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B201F41D")));
                //efContract1 - dumplist index 3
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B2014C1D")));
                //efContract2 - dumplist index 4
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B2024C1D")));
                //efContract3 - dumplist index 5
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B2034C1D")));
                //efContract4 - dumplist index 6
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B2044C1D")));
                //efContract5 - dumplist index 7
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B2054C1D")));
                //efContract6 - dumplist index 8
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B2064C1D")));
                //efContract7 - dumplist index 9
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B2074C1D")));
                //efContract8 - dumplist index 10
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B2084C1D")));
                //efEventLogs1 - dumplist index 11
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B201441D")));
                //efEventLogs2 - dumplist index 12
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B202441D")));
                //efEventLogs3 - dumplist index 13
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B203441D")));
                //efValidation - dumplist index 14
                dumplist.add(isoDep.transceive(hexStringToByteArray("00B201CC1D")));

                setContentBuffer(dumplist);

                if(dumplist.size() == 15 && dumplist.get(1)[0] != 0) {
                    if(dumplist.get(2)[1] == 0) {
                        showToastLong(context.getString(R.string.smartcard_empty));
                        return;
                    }
                    showToastLong(context.getString(R.string.smartcard_read_correctly));
                } else {
                    showToastLong(context.getString(R.string.invalid_smartcard));
                }

                isoDep.close();

            } catch (IOException e) {
                setContentBuffer(new ArrayList<>());
                showToastLong(context.getString(R.string.read_failure));
            }
        }
    }


    private void handleNfcA(Tag tagFromIntent) {

        final NfcA mfu = NfcA.get(tagFromIntent);

        if (mfu == null) {
            setContentBuffer(new ArrayList<>());
            showToastLong(context.getString(R.string.ticket_not_supported));
            return;
        }

        byte[] ATQA = mfu.getAtqa();

        if (mfu.getSak() != 0x00 || ATQA.length != 2 || ATQA[0] != 0x44 || ATQA[1] != 0x00) {
            setContentBuffer(new ArrayList<>());
            showToastLong(context.getString(R.string.ticket_not_supported));
            return;
        }

        int pagesRead;
        List<byte[]> dumplist = new ArrayList<>();

        try {
            mfu.connect();
            pagesRead = rdNumPages(mfu, 16); // 0 for no limit (until error)
            mfu.close();

            for (int i = 0; i < pagesRead*4; i += 4) {
                byte[] mfuPage = new byte[4];
                System.arraycopy(readBuffer, i, mfuPage, 0,  4);
                dumplist.add(mfuPage);
            }

            setContentBuffer(dumplist);
            if(pagesRead >= 16) {
                showToastShort(context.getString(R.string.ticket_correctly_read));
            } else {
                throw new RuntimeException(context.getString(R.string.read_failure));
            }

        }
        catch (RuntimeException e) {
            setContentBuffer(new ArrayList<>());
            showToastLong(context.getString(R.string.read_failure));
        }
        catch (Exception e) {
            setContentBuffer(new ArrayList<>());
            showToastLong(context.getString(R.string.communication_error));
        }
    }

    private void setContentBuffer(List<byte[]> content) {
        Message msg = new Message();
        msg.obj = content;
        mTextBufferHandler.sendMessage(msg);
    }

    private void showToastShort(String text) {
        Message msg = new Message();
        msg.obj = text;
        mToastShortHandler.sendMessage(msg);
    }

    private void showToastLong(String text) {
        Message msg = new Message();
        msg.obj = text;
        mToastLongHandler.sendMessage(msg);
    }

    private void showInfoDialog(String text) {
        Message msg = new Message();
        msg.obj = text;
        mShowInfoDialogHandler.sendMessage(msg);
    }


    private int rdNumPages(NfcA mfu, int num) {
        int pagesRead = 0;

        while (rdPages(mfu, pagesRead) == 0) {
            pagesRead++;
            if (pagesRead == num || pagesRead == 256) break;
        }
        return pagesRead;
    }

    // first failure (NAK) causes response 0x00 (or possibly other 1-byte values)
    // second failure (NAK) causes transceive() to throw IOException
    private byte rdPages(NfcA tag, int pageOffset) {
        byte[] cmd = {0x30, (byte)pageOffset};
        byte[] response = new byte[16];
        try {
            response = tag.transceive(cmd);
        }
        catch (IOException e) {
            return 1;
        }
        if (response.length != 16)
            return 1;

        System.arraycopy(response, 0, readBuffer, pageOffset * 4, 4);
        return 0;
    }

}

