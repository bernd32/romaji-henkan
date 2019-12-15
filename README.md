
# romaji-henkan
Romaji-henkan is a simple open-source Java library for converting Japanese text to Latin alphabet (romaji)

## Example:
Add the dependency to your `pom.xml`:
```xml
<dependency>
  <groupId>io.github.bernd32</groupId>
  <artifactId>romaji-henkan</artifactId>
  <version>0.0.1</version>
</dependency>
```
The following code converts string in Japanese to romaji:
```java
import com.bernd32.romajihenkan.RomajiHenkan;
public class RomajiHenkanExample {
    public static void main(String[] args) {
		RomajiHenkan henkan = new RomajiHenkan();
		System.out.println(henkan.convert("自己紹介の最後に添える挨拶として使う表現")); 
	}
}
```
 Output: 
```
jikō shōkai nō saigō ni sōeru aisatsu tōshite tsukau hyōgen
```
## Additional information
Powered by [kuromoji](https://github.com/atilika/kuromoji), some part of the code were taken from [moji4j](https://github.com/andree-surya/moji4j).  
This library is using IPADIC dictionary.

## License

   Romaji-henkan is licensed under the MIT License. See `LICENSE.md` for details.
