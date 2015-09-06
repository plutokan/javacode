package com.cisco.rekan.jdbc;
/*
        start_ip_int
        end_ip_int
        continent_id
        continent_cf
        country_id
        country_iso2
        country_cf
        state_id
        state_cf
        city_id
        city_cf
        latitude
        longitude
        sld_id
        tld_id
        asn          (new field as of 2005/10/18)
        reg_org_id
        carrier_id    
*/ 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;

public class Query {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 1) {
			long i = 0;
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			try {
				// FileReader fr = new FileReader(args[0]);
				FileReader fr = new FileReader("webex_2006-09-21.dat");
				BufferedReader br = new BufferedReader(fr);
				
				// Continue to read lines while there are still some left to read
				String record;
				String result;
				while ((record = br.readLine()) != null) {
					result = getCityISP(record);
					if (map.containsKey(result)) {
						map.put(result, new Integer(map.get(result).intValue() + 1));
					} else {
						map.put(result, new Integer(1));
						i++;
					}
					//System.out.println(result);
				}
				System.out.println("read count number : " + i);
				br.close();
				fr.close();
				
				FileWriter fw = new FileWriter("new2.data");
				BufferedWriter bw = new BufferedWriter(fw);
				
				i=0;
				Iterator it = map.keySet().iterator();
				String tempkey;
				while (it.hasNext()) {
					tempkey = it.next().toString();
					bw.write(tempkey + map.get(tempkey));
					bw.newLine();
					i++;
				}
				System.out.println("write count number : " + i);
				bw.close();
				fw.close();
			} catch (Exception e) {
				System.err.println("Uh oh, got an IOException error!");
			}
		} else {
			System.out.println("Invalid parameters");
		}
	}
	
	private static String getCityISP(String record) {
		String result = "|";
		record = record.substring(record.indexOf("|") + 1);
		record = record.substring(record.indexOf("|") + 1);
		result = result + record.substring(0, record.indexOf("|")) + "|";    // continent_id
		record = record.substring(record.indexOf("|") + 1);
		record = record.substring(record.indexOf("|") + 1);
		result = result + record.substring(0, record.indexOf("|")) + "|";    // country_id
		record = record.substring(record.indexOf("|") + 1);
		record = record.substring(record.indexOf("|") + 1);
		record = record.substring(record.indexOf("|") + 1);
		result = result + record.substring(0, record.indexOf("|")) + "|";    // state_id
		record = record.substring(record.indexOf("|") + 1);
		record = record.substring(record.indexOf("|") + 1);
		result = result + record.substring(0, record.indexOf("|")) + "|";    // city_id
		record = record.substring(0, record.length() - 1);
		result = result + record.substring(record.lastIndexOf("|") + 1) + "|";    // carrier_id
		
		return result;
	}

}
