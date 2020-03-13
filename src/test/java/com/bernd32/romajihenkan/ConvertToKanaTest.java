package com.bernd32.romajihenkan;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConvertToKanaTest {
	
	RomajiHenkan henkan; 

	@Before
	public void setUp() throws Exception {
		henkan = new RomajiHenkan(); 
	}
	
	@Test 
	public void geographyKanjiTesting() {
		assertEquals("tōkyō", henkan.convert("東京")); 
		assertEquals("yokohama", henkan.convert("横浜")); 
		assertEquals("ōsaka", henkan.convert("大阪")); 
		assertEquals("nagoya", henkan.convert("名古屋")); 
	}
	
	@Test
	public void sentenceTesting() {
		assertEquals("bijinesu meru nadō de kekku ni mōchiiru hyōgen", 
				henkan.convert("ビジネスメールなどで結句に用いる表現")); 
		assertEquals("tanomi ni kotae te morau kansha no kimochi wo arawasu baai", 
				henkan.convert("頼みに答えてもらう感謝の気持ちを表す場合")); 
		assertEquals("kaigai zaiju nō yujin kara meru wō uketōtta baai nadō ni , renraku wō kure ta kōtō nitaishite ōrei wō iu baai", 
				henkan.convert("海外在住の友人からメールを受け取った場合などに、連絡をくれたことに対してお礼を言う場合")); 
		assertEquals("jikō shōkai nō saigō ni sōeru aisatsu tōshite tsukau hyōgen", 
				henkan.convert("自己紹介の最後に添える挨拶として使う表現")); 
	}
	
	@Test
	public void sokuonTesting() {
		assertEquals("ratte", henkan.convert("ラッテ"));
		assertEquals("ko nakatta", henkan.convert("来なかった"));
	}
	
	@Test
	public void test() {
		System.out.println(henkan.convert("思い通りに行かなくて　プツリ黙りこくる"));
	}

}
