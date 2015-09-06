package com.cisco.rekan.enumtype;

public class Sample3 {
	  enum Size {
	    Small {
	      public double getPricingFactor() {
	        return 0.8;
	      }
	    },
	    Medium,
	    Large,
	    ExtraLarge {
	      public double getPricingFactor() {
	        return 1.2;
	      }
	    },
	    ExtraExtraLarge {
	      public double getPricingFactor() {
	        return 1.2;
	      }
	    };
	    public double getPricingFactor() {
	      return 1.0;
	    }
	  }
	  public static void main(String args[]) {
		  System.out.println(Size.class);
		  System.out.println();
	    for (Size s : Size.values()) {
	    	System.out.println(s.getClass());
	        System.out.println(s.getDeclaringClass());
	      double d = s.getPricingFactor();
	      System.out.println(s + " Size has pricing factor of " + d);
	    }
	  }
}
