package com.cisco.rekan.enumtype;
/**
 * The Java Programming Language - Fourth Edition
 * ��ϰ6.1: Ϊ��ͨ�Ƶ���ɫ����򵥵�ö��
 * ��ϰ6.4: ������ϰ6.1�й����Ľ�ͨ����ɫ��ö�٣�ʹ��ÿ��ö�ٳ�������һ����֮����
 *         ��Color��������������ͨ��getColor������á�
 * ��ϰ6.5: ������ϰ6.4����getColor������ɳ��󷽷�����Ϊÿ��ö�ٳ������峣����
 *         �صķ����������Ƿ�����ȷ��Color��������ΪӦ��ʹ�ó�����صķ�����ʵ����
 */

 // 6.4
enum TL {
	RED("red"),
	YELLOW("yellow"),
	GREEN("green"),
	;
	class Color {
		String color;
		
		Color (String color) {
			this.color = color;
		}
		
		public String toString() {
			return "---" + color;
		}
	}
	
	Color colour;
	Color getColor() {
		return colour;
	}
	
	TL(String color) {
		colour = new Color(color);
	}
}

/*
// 6.5
enum TL {
	RED {
		Color getColor() {
			return new Color("-red-");
		}
	},
	YELLOW {
		Color getColor() {
			return new Color("-yellow-");
		}
	},
	GREEN {
		Color getColor() {
			return new Color("-green-");
		}
	},
	;
	class Color {
		String color;
		
		Color (String color) {
			this.color = color;
		}
		
		public String toString() {
			return color;
		}
	}
	
	Color colour;
	abstract Color getColor();
	
}
*/
public class TrafficLight {
    
    public static void main(String argv[]) {
    	for (TL tl : TL.values()) {
    		System.out.println(tl.name() + tl.ordinal() + tl.toString() + tl.getColor().toString());
    	}
    }
    
}
