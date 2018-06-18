package algorithms.strings;

import algorithms.strings.alphabet.Alphabet;
import algorithms.strings.alphabet.EnumerateAlphabet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StringsContext {

  private static final EnumerateAlphabet.CharRange LOWER_CASE = new EnumerateAlphabet.CharRange('a', 'z');
  private static final EnumerateAlphabet.CharRange UPPER_CASE = new EnumerateAlphabet.CharRange('A', 'Z');
  private static final EnumerateAlphabet.CharRange NUMBERS = new EnumerateAlphabet.CharRange('0', '9');
  private static final String PUNCTUATION = " !\"'~`!@#$%^&*()_+-=,.<>/?:;[]{}\\|\t\n";

  @Bean
  public Alphabet lowercaseAlphabet() {
    return EnumerateAlphabet.builder().appendCharRange(LOWER_CASE).build();
  }

  @Bean
  public Alphabet abcAlphabet() {
    return EnumerateAlphabet.builder().appendString("ABC").build();
  }

  @Bean
  public Alphabet numericAlphabet() {
    return EnumerateAlphabet.builder().appendCharRange(NUMBERS).build();
  }

  @Bean
  public Alphabet englishAlphabet() {
    EnumerateAlphabet.Builder builder = EnumerateAlphabet.builder();
    builder.appendCharRange(LOWER_CASE);
    builder.appendCharRange(UPPER_CASE);
    builder.appendCharRange(NUMBERS);
    builder.appendString(PUNCTUATION);
    return builder.build();
  }

}
