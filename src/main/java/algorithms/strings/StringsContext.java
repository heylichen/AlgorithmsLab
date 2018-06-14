package algorithms.strings;

import algorithms.strings.alphabet.Alphabet;
import algorithms.strings.alphabet.EnumerateAlphabet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StringsContext {

  @Bean
  public Alphabet lowercaseAlphabet() {
    return EnumerateAlphabet.builder().appendCharRange('a', 'z').build();
  }

  @Bean
  public Alphabet abcAlphabet() {
    return EnumerateAlphabet.builder().appendString("ABC").build();
  }

  @Bean
  public Alphabet numericAlphabet() {
    return EnumerateAlphabet.builder().appendString("0123456789").build();
  }
}
