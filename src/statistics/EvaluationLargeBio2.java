package statistics;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EvaluationLargeBio2 {
	int numOfRulesGold=0;
	int numOfRulesMatcher=0;
	int numOfRulesCorrect=0;
	int numOfRulesRevised=0;
	Set <String> revised=new HashSet<String>();
	public EvaluationLargeBio2(ArrayList<String> mapping, ArrayList<String> reference) {
		// Mapping correct = reference.getIntersection(mapping); 
		Set <String> correct=new HashSet<String>();
		
		int subNumber=0;
		for (String r : reference) 
		{
			String rParts[]=r.split(",");
			for (String m : mapping) 
			{
				String mParts[]=m.split(",");
				if (rParts[0].equals(mParts[0])&& rParts[1].equals(mParts[1])&&mParts[2].equals("=")&&rParts[2].equals("=")) 
				{
					correct.add(r);
				}
				else if(rParts[0].equals(mParts[1])&& rParts[1].equals(mParts[0])&&mParts[2].equals("=")&&rParts[2].equals("="))			
				{
					correct.add(r);
				}	
				//�����Ѿ�������ReferenceAlignment�е��ʺ�
				else if(rParts[0].equals(mParts[1])&& rParts[1].equals(mParts[0])&&mParts[2].equals("=")&&mParts[2].equals("?"))			
				{
					correct.add(r);
					revised.add(r);
				}	
				else if (rParts[0].equals(mParts[0])&& rParts[1].equals(mParts[1])&&mParts[2].equals("=")&&mParts[2].equals("?")) 
				{
					correct.add(r);
					revised.add(r);
				}
				if(mParts[2].equals("sub"))
					subNumber++;
				//���Ƕ�Revised�Ĳ��ֽ���ͳ��
				if(mParts[2].equals("?"))
					numOfRulesRevised++;
			}
		}		
		this.numOfRulesGold = reference.size();		
		this.numOfRulesMatcher = mapping.size()-subNumber/reference.size();
		this.numOfRulesCorrect = correct.size();
		this.numOfRulesRevised = numOfRulesRevised/reference.size();
	}	
	
	public String toShortDesc() {
		double precision = this.getPrecision();
		double recall = this.getRecall();
		double f = this.getFMeasure();

		return toDecimalFormat(precision) + "  " + toDecimalFormat(recall) + "  " + toDecimalFormat(f);
	}
	
	public double getPrecision() {
		return (double)this.numOfRulesCorrect /  (double)this.numOfRulesMatcher;
		//return (double)this.numOfRulesCorrect /  ((double)this.numOfRulesCorrect+(double)this.numOfRulesInCorrect);
	}
	
	public double getRecall() {
		return (double)this.numOfRulesCorrect /  (double)this.numOfRulesGold;
	}
	
	public double getFMeasure() {
		if ((this.getPrecision() == 0.0f) || (this.getRecall() == 0.0f)) { return 0.0f; }
		return (2 * this.getPrecision() * this.getRecall()) / (this.getPrecision() + this.getRecall());
	}
	
	public int getMatcherAlignment() {
		return numOfRulesMatcher;
	}
	
	public int getCorrectAlignment() {
		return numOfRulesCorrect;
	}
	
	public int getRevisedNum() {
		return numOfRulesRevised;
	}
	
	public int getCorrectRevisedNum() {
		return revised.size();
	}
	
	public void getRevisedInformation() {
		System.out.println("The total of revised mappings is "+numOfRulesRevised);
		System.out.println("The number of correct revised mappings is "+revised.size());
	}
	
	
	
	private static String toDecimalFormat(double precision) {
		DecimalFormat df = new DecimalFormat("0.000");
		return df.format(precision).replace(',', '.');
	}
		
}
