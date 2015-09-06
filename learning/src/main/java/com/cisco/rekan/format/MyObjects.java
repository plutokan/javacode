package com.cisco.rekan.format;

import java.util.FormattableFlags;
import java.util.Formatter;
import java.util.Formattable;
public class MyObjects implements Formattable {
  String name;
  public MyObjects(String name) {
    this.name = name;
  }
  public void formatTo(
         Formatter fmt,
         int f,
         int width,
         int precision) {
    StringBuilder sb = new StringBuilder();
      // no max width
    if (name.length() < precision) {
      sb.append(name);
    } else {
      sb.append(name.substring(0, precision - 1)).append('*');
    }
    // apply width and justification
    if ( (sb.length() < width)) {
      for (int i = 0, n=sb.length(); i < width - n; i++) {
        if ((f & FormattableFlags.LEFT_JUSTIFY) == FormattableFlags.LEFT_JUSTIFY) {
          sb.append(' ');
        } else {
          sb.insert(0, ' ');
        }
      }
    }
    fmt.format(sb.toString());
  }
  public static void main(String args[]) {
   MyObjects my1 = new MyObjects("John");
   MyObjects my2 = new MyObjects("Really Long Name");
   // First / Using toString()
   System.out.println("First Object : " + my1);
   // Second / Using Formatter
   System.out.format("First Object : '%s'\\n", my1);
   // Second / Using Formatter
   System.out.format("Second Object: '%s'\\n", my2);
   // Second / Using Formatter with width
   System.out.format("Second Object: '%10.5s'\\n", my2);
   // Second / Using Formatter with width and left justification
   System.out.format("Second Object: '%-10.5s'\\n", my2);
  }
}
