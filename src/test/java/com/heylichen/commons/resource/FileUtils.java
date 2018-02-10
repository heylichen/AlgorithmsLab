package com.heylichen.commons.resource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Chen Li on 2017/12/21.
 */
@Slf4j
public class FileUtils {



  public static File getFile(String classpath) {
    File f;
    try {
      f = new ClassPathResource(classpath).getFile();
      return f;
    } catch (IOException e) {
      log.error("loading file error, path={}", classpath, e);
      return null;
    }
  }

  public static List<String> readLines(String classpath) {
    File f;
    try {
      f = new ClassPathResource(classpath).getFile();
    } catch (IOException e) {
      log.error("loading file error, path={}", classpath, e);
      return Collections.emptyList();
    }
    try {
      return org.apache.commons.io.FileUtils.readLines(f, StandardCharsets.UTF_8);
    } catch (IOException e) {
      log.error("loading file error, path={}", classpath, e);
      return Collections.emptyList();
    }
  }
}
