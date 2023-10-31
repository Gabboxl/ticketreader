package org.dslul.ticketreader;


import org.dslul.ticketreader.models.ChipOnPaper;
import org.dslul.ticketreader.models.SmartCard;
import org.dslul.ticketreader.util.GttDate;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.dslul.ticketreader.util.HelperFunctions.hexStringToByteArray;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TicketsUnitTest {

    @Test
    public void ChipOnPaper_isCorrect() {
        List<byte[]> ticket1 = new ArrayList<>();
        ticket1.add(hexStringToByteArray("057D6292"));
        ticket1.add(hexStringToByteArray("AD2954E9"));
        ticket1.add(hexStringToByteArray("3915F203"));
        ticket1.add(hexStringToByteArray("07FFFFF0"));
        ticket1.add(hexStringToByteArray("01040000"));
        ticket1.add(hexStringToByteArray("020102BE"));
        ticket1.add(hexStringToByteArray("68970000"));
        ticket1.add(hexStringToByteArray("00AE10A7"));
        ticket1.add(hexStringToByteArray("0200645C"));
        ticket1.add(hexStringToByteArray("397D91B4"));
        ticket1.add(hexStringToByteArray("68A4F900"));
        ticket1.add(hexStringToByteArray("04F80000"));
        ticket1.add(hexStringToByteArray("68A4F900"));
        ticket1.add(hexStringToByteArray("00050004"));
        ticket1.add(hexStringToByteArray("F8AE1079"));
        ticket1.add(hexStringToByteArray("9E1291E4"));
        ChipOnPaper chip = new ChipOnPaper(ticket1);
        assertEquals(4, chip.getRemainingRides());
        assertEquals(0, chip.getRemainingMinutes());


        ticket1.set(0, hexStringToByteArray("043FDA69"));
        ticket1.set(1, hexStringToByteArray("AAD75B81"));
        ticket1.set(2, hexStringToByteArray("A748F203"));
        ticket1.set(3, hexStringToByteArray("7FFFFFFF"));
        ticket1.set(4, hexStringToByteArray("010B0000"));
        ticket1.set(5, hexStringToByteArray("0101012F"));
        ticket1.set(6, hexStringToByteArray("6F072000"));
        ticket1.set(7, hexStringToByteArray("00AE10A7"));
        ticket1.set(8, hexStringToByteArray("F1006A3D"));
        ticket1.set(9, hexStringToByteArray("99EE380F"));
        ticket1.set(10, hexStringToByteArray("6F09BE00"));
        ticket1.set(11, hexStringToByteArray("04F80000"));
        ticket1.set(12, hexStringToByteArray("6F09BE00"));
        ticket1.set(13, hexStringToByteArray("00D80004"));
        ticket1.set(14, hexStringToByteArray("F8AE10A0"));
        ticket1.set(15, hexStringToByteArray("02121827"));
        chip = new ChipOnPaper(ticket1);
        assertEquals(0, chip.getRemainingRides());
        assertEquals(0, chip.getRemainingMinutes());


        ticket1.set(0, hexStringToByteArray("04CF7132"));
        ticket1.set(1, hexStringToByteArray("1A675D80"));
        ticket1.set(2, hexStringToByteArray("A048F203"));
        ticket1.set(3, hexStringToByteArray("7FFFFFFF"));
        ticket1.set(4, hexStringToByteArray("01030000"));
        ticket1.set(5, hexStringToByteArray("0101012E"));
        ticket1.set(6, hexStringToByteArray("6C0FC000"));
        ticket1.set(7, hexStringToByteArray("00AE112F"));
        ticket1.set(8, hexStringToByteArray("C9000152"));
        ticket1.set(9, hexStringToByteArray("A22C4C8E"));
        ticket1.set(10, hexStringToByteArray("6C958400"));
        ticket1.set(11, hexStringToByteArray("04F80000"));
        ticket1.set(12, hexStringToByteArray("6C958400"));
        ticket1.set(13, hexStringToByteArray("000F0004"));
        ticket1.set(14, hexStringToByteArray("F8AE10FE"));
        ticket1.set(15, hexStringToByteArray("C7123D71"));
        chip = new ChipOnPaper(ticket1);
        assertEquals(0, chip.getRemainingRides());
        assertEquals(0, chip.getRemainingMinutes());


        ticket1.set(10, hexStringToByteArray(GttDate.genDate()));
        chip = new ChipOnPaper(ticket1);
        assertEquals(100, chip.getRemainingMinutes());
    }

    @Test
    public void Smartcard_Count_Ticket_isCorrect() {
        List<byte[]> list = new ArrayList<>();
        list.add(hexStringToByteArray("6F208970ABA0B980986C9A09078F098087E0A980DF0101010A0101090E109019011022354345676010019000"));
        list.add(hexStringToByteArray("0501030C72BF2000026B0CC4435354504C413639423632443936394DC09000"));
        list.add(hexStringToByteArray("05010100016010016120000000000000000000000000000000000000009000"));
        list.add(hexStringToByteArray("01050000000000000094F33B94ED9CFFAE10AC40007661A00092EB6D539000"));
        list.add(hexStringToByteArray("0105000002CC000000930F7B8B267CFFAE10A71800F7BFA000B8CC23E69000"));
        list.add(hexStringToByteArray("0105000002CC0000009228DB8A3FDCFFAE10A718011440A000F722D0509000"));
        list.add(hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.add(hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.add(hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.add(hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.add(hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.add(hexStringToByteArray("0501000000216E34F700000B00032100000000006E34F700003000E7BC9000"));
        list.add(hexStringToByteArray("0501000000216E347B00001400032100000000006E347B000030008A999000"));
        list.add(hexStringToByteArray("0501000000216E329D00000700032100000000006E329D0000300083079000"));
        list.add(hexStringToByteArray("06D3DD37A48600000000000000000000000000000000000000000000009000"));
        SmartCard smartcard = new SmartCard(list);

        assertEquals(6, smartcard.getRemainingRides());

        list.set(1, hexStringToByteArray("0501030C72BB2000026B0CC4535643534C5630315035304C32313941C09000"));
        list.set(2, hexStringToByteArray("05010100016010016120000000000000000000000000000000000000009000"));
        list.set(3, hexStringToByteArray("01050000000000000094F33B94ED9CFFAE10AC40007660A00012425D4A9000"));
        list.set(4, hexStringToByteArray("0105000002CC000000930F7B8B267CFFAE10A71800F7C0A0003DE647B39000"));
        list.set(5, hexStringToByteArray("0105000002CC0000009228DB8A3FDCFFAE10A718011441A0008AD75F7F9000"));
        list.set(6, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(7, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(8, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(9, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(10, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(11, hexStringToByteArray("0501000000216E3B7600000700032100000000006E3B76000030001BD19000"));
        list.set(12, hexStringToByteArray("0501000000216E1DCD00000B00032100000000006E1DCD00003000D3669000"));
        list.set(13, hexStringToByteArray("0501000000216E194200000F00032100000000006E194200003000089A9000"));
        list.set(14, hexStringToByteArray("06F4161F9BAD00000000000000000000000000000000000000000000009000"));
        smartcard = new SmartCard(list);

        assertEquals(3, smartcard.getRemainingRides());


        list.set(1, hexStringToByteArray("0501030BE11820000269F05D524D4E42524E36314530384C3231394FC09000"));
        list.set(2, hexStringToByteArray("05014120014110000000000000000000000000000000000000000000009000"));
        list.set(3, hexStringToByteArray("0105000002CB000000921D9B8A349CFFAE10A3CA00A4CCA00025EFFF919000"));
        list.set(4, hexStringToByteArray("0105000002CB000000921D9B8A349CFFAE10A3CA00A4CDA000E885A0709000"));
        list.set(5, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(6, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(7, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(8, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(9, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(10, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(11, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(12, hexStringToByteArray("0501000000216DE5E60004F800000900000000006DE59D00001000A5079000"));
        list.set(13, hexStringToByteArray("0501000000216DE5E20004F800000900000000006DE59D00001000D1C69000"));
        list.set(14, hexStringToByteArray("0FFFFF0FFFFF00000000000000000000000000000000000000000000009000"));
        smartcard = new SmartCard(list);

        assertEquals(2, smartcard.getRemainingRides());


        list.set(1, hexStringToByteArray("0501030C544E2000026AFE93424E5444564436334130364439333348C09000"));
        list.set(2, hexStringToByteArray("05016120000000000000000000000000000000000000000000000000009000"));
        list.set(3, hexStringToByteArray("0105000002CC00000092DCDB8AF3DCFFAE10A6B800AEFBA00045509B0E9000"));
        list.set(4, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(5, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(6, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(7, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(8, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(9, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(10, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(11, hexStringToByteArray("0501000000216DDB0E0004F80003E700000000006DD8A900001000F0639000"));
        list.set(12, hexStringToByteArray("0501000000216DD8A90004F800004700000000006DD8A900001000EF2A9000"));
        list.set(13, hexStringToByteArray("0501000000216D653E0004F800004700000000006D62A80000100092DC9000"));
        list.set(14, hexStringToByteArray("000000274A7A00000000000000000000000000000000000000000000009000"));
        smartcard = new SmartCard(list);

        assertEquals(4, smartcard.getRemainingRides());


        list.set(1, hexStringToByteArray("05010304CEBD2010023E3F64534C4D4E544E3934413135413834314FC29000"));
        list.set(2, hexStringToByteArray("0501A100000000000000000000000000000000000000000000000000009000"));
        list.set(3, hexStringToByteArray("010503900390000000C27A3BBA751BFFAE10AC480002AEA0005C37C9E19000"));
        list.set(4, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(5, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(6, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(7, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(8, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(9, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(10, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(11, hexStringToByteArray("0501000000214410E000000B00032100000000004410E000000000EB969000"));
        list.set(12, hexStringToByteArray("05010000002143D37700000D000321000000000043D3770000000048749000"));
        list.set(13, hexStringToByteArray("0501000000214313F500000D00032100000000004313F50000000068FA9000"));
        list.set(14, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        smartcard = new SmartCard(list);

        assertEquals(0, smartcard.getRemainingRides());


        list.set(1, hexStringToByteArray("05010304864E2010023DA7844C444E444E4C39334D30384638393245C29000"));
        list.set(2, hexStringToByteArray("0501A10001611001A100000000000000000000000000000000000000009000"));
        list.set(3, hexStringToByteArray("010503900390000000C27A3BBA751BFFAE1045EC00004AA000271C893D9000"));
        list.set(4, hexStringToByteArray("0105000002CC00000092233B8A3A3CFFAE10A7F10052A3A000065CFC0B9000"));
        list.set(5, hexStringToByteArray("01050000038F0302589A5AFB93B29CFFAE10FEC000007FA00003D605A29000"));
        list.set(6, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(7, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(8, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(9, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(10, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(11, hexStringToByteArray("0501000000216E175E0004F800000C00000000006E175E00002000E9AA9000"));
        list.set(12, hexStringToByteArray("0501000000216DFD4D0004F800000A00000000006DFD3600002000832A9000"));
        list.set(13, hexStringToByteArray("0501000000216DFD3600000100032100000000006DFD360000200057569000"));
        list.set(14, hexStringToByteArray("2FC56500000000000000000000000000000000000000000000000000009000"));
        smartcard = new SmartCard(list);

        assertEquals(5, smartcard.getRemainingRides());


        list.set(0, hexStringToByteArray("6F28840E315449432E494341D38012009101A516BF0C13C70800000000381ADC645307063C23C01010019000"));
        list.set(1, hexStringToByteArray("0501030D1E7B2000026C5AAC434C4D4C534E3630433231473232344BC09000"));
        list.set(2, hexStringToByteArray("05012110012120012130012140012150000000000000000000000000009000"));
        list.set(3, hexStringToByteArray("0105000002C800000093CEBB83BEDCFFAE10AC64014437A0005485E4479000"));
        list.set(4, hexStringToByteArray("0105000002CA00000091B85B81AE1CFFAE10AA0C002857A000E0E8BAA89000"));
        list.set(5, hexStringToByteArray("0105000002CA00000091B85B81AE1CFFAE10AA0C002858A000292F7D039000"));
        list.set(6, hexStringToByteArray("0105000002CA00000091B85B81AE1CFFAE10AA0C002859A000051117559000"));
        list.set(7, hexStringToByteArray("0105000002CA00000091B85B81AE1CFFAE10AA0C00285AA000FBDD7F709000"));
        list.set(8, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(9, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(10, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(11, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(12, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(13, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(14, hexStringToByteArray("00000900000900000900000900000900000000000000000000000000009000"));
        smartcard = new SmartCard(list);

        assertEquals(5, smartcard.getRemainingRides());


        list.set(1, hexStringToByteArray("05010304864E2010023DA7844C444E444E4C39334D30384638393245C29000"));
        list.set(2, hexStringToByteArray("0501A10001611001A100014120000000000000000000000000000000009000"));
        list.set(3, hexStringToByteArray("010503900390000000C27A3BBA751BFFAE1045EC00004AA000271C893D9000"));
        list.set(4, hexStringToByteArray("0105000002CC00000092233B8A3A3CFFAE10A7F10052A3A000065CFC0B9000"));
        list.set(5, hexStringToByteArray("01050000038F0302589A5AFB93B29CFFAE10FEC000007FA00003D605A29000"));
        list.set(6, hexStringToByteArray("0105000002CB00000090501F886720FFAE10A7BD007D57A000C743C9519000"));
        list.set(7, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(8, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(9, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(10, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(11, hexStringToByteArray("0501000000216F98540004F800000F00000000006F979900002000D87B9000"));
        list.set(12, hexStringToByteArray("0501000000216F97990004F800000F00000000006F97990000200051459000"));
        list.set(13, hexStringToByteArray("0501000000216EFB420004F800000F00000000006EF8870000200015E39000"));
        list.set(14, hexStringToByteArray("0645660FFFFF00000000000000000000000000000000000000000000009000"));
        smartcard = new SmartCard(list);

        assertEquals(1, smartcard.getRemainingRides());
        assertEquals("EDISU - Daily", smartcard.getTicketName());


        list.set(1, hexStringToByteArray("05010304864E2010023DA7844C444E444E4C39334D30384638393245C29000"));
        list.set(2, hexStringToByteArray("0501A10001601001A100014120000000000000000000000000000000009000"));
        list.set(3, hexStringToByteArray("010503900390000000C27A3BBA751BFFAE1045EC00004AA000271C893D9000"));
        list.set(4, hexStringToByteArray("0105000002CC00000092233B8A3A3CFFAE10A7F10052A3A000065CFC0B9000"));
        list.set(5, hexStringToByteArray("01050000038F0302589A5AFB93B29CFFAE10FEC000007FA00003D605A29000"));
        list.set(6, hexStringToByteArray("0105000002CB00000090501F886720FFAE10A7BD007D57A000C743C9519000"));
        list.set(7, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(8, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(9, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(10, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(11, hexStringToByteArray("0501000000216FB3A20004F800000F00000000006FB3A200004000210C9000"));
        list.set(12, hexStringToByteArray("0501000000216F98540004F800000F00000000006F979900002000D87B9000"));
        list.set(13, hexStringToByteArray("0501000000216F97990004F800000F00000000006F97990000200051459000"));
        list.set(14, hexStringToByteArray("06456607FC3D00000000000000000000000000000000000000000000009000"));
        smartcard = new SmartCard(list);

        assertEquals(0, smartcard.getRemainingRides());


        list.set(1, hexStringToByteArray("050103089E8D2000025DC1A4524E44524C4130304432324C32313959C19000"));
        list.set(2, hexStringToByteArray("0501A00001F00001A10001F10001A00001F000000000000000000000009000"));
        list.set(3, hexStringToByteArray("010500000FA100000094231B93FBBCFFAE10AC7000D9B0A0026D5A4AC69000"));
        list.set(4, hexStringToByteArray("F110060000000000000051504F415500000000000000000000BA9E15609000"));
        list.set(5, hexStringToByteArray("010500000FA100000091FBDB91D47CFFAE10AD3A005C23A00426C74CDE9000"));
        list.set(6, hexStringToByteArray("F130040000000000000051504F41000000000000000000000071AB2ADB9000"));
        list.set(7, hexStringToByteArray("010500000FA100000096717F964A20FFAE10ACB40042C6A006ECF2BC6E9000"));
        list.set(8, hexStringToByteArray("F150040000000000000051504F4100000000000000000000008A523C0E9000"));
        list.set(9, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(10, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(11, hexStringToByteArray("0501000000216E05DE0004490005E600000000006E05DE0000300017379000"));
        list.set(12, hexStringToByteArray("0501000000216BF78A0004000005E600000000006BE44200001000EAB79000"));
        list.set(13, hexStringToByteArray("0501000000216BF75E0004F800002200000000006BE4420000100059469000"));
        list.set(14, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        smartcard = new SmartCard(list);

        assertFalse(smartcard.hasTickets());
        assertTrue(smartcard.hasSubscriptions());
        assertEquals(0, smartcard.getRemainingRides());


        list.set(1, hexStringToByteArray("0501030888942011F24E44044C4D544652433931443539433334324FC29000"));
        list.set(2, hexStringToByteArray("0501A100010100014110000000000000000000000000000000000000009000"));
        list.set(3, hexStringToByteArray("0105000003E10388A096609F8E5B80FFAE10FE4F0000A0A000674A13489000"));
        list.set(4, hexStringToByteArray("0105000003900130FDAA6ADBA2601CFFAE1046110016DAA00032711AF99000"));
        list.set(5, hexStringToByteArray("0105000002CB00000092887B8A9F7CFFAE10AAE8003EBAA00037DBB1FE9000"));
        list.set(6, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(7, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(8, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(9, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(10, hexStringToByteArray("00000000000000000000000000000000000000000000000000000000009000"));
        list.set(11, hexStringToByteArray("05010388A0216E1EBA0004F800001200000000006D95A700001000FA1A9000"));
        list.set(12, hexStringToByteArray("05010388A0216DF7540004F800004400000000006D95A70000100091A49000"));
        list.set(13, hexStringToByteArray("05010388A0216DCDFA0004F800000400000000006D95A70000100041CD9000"));
        list.set(14, hexStringToByteArray("07FDFA00000000000000000000000000000000000000000000000000009000"));
        smartcard = new SmartCard(list);

        assertFalse(smartcard.hasTickets());
        assertTrue(smartcard.hasSubscriptions());
        assertEquals(0, smartcard.getRemainingRides());


    }


}