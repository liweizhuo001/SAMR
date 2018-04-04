/******************************************************************************
* Copyright 2013-2016 LASIGE                                                  *
*                                                                             *
* Licensed under the Apache License, Version 2.0 (the "License"); you may     *
* not use this file except in compliance with the License. You may obtain a   *
* copy of the License at http://www.apache.org/licenses/LICENSE-2.0           *
*                                                                             *
* Unless required by applicable law or agreed to in writing, software         *
* distributed under the License is distributed on an "AS IS" BASIS,           *
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.    *
* See the License for the specific language governing permissions and         *
* limitations under the License.                                              *
*                                                                             *
*******************************************************************************
* A filtering algorithm based on logical coherence.                           *
*                                                                             *
* @author Daniel Faria & Emanuel Santos                                       *
******************************************************************************/
package aml.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.sun.org.apache.bcel.internal.generic.IfInstruction;

import aml.AML;
import aml.match.Mapping;
import aml.ontology.DataProperty;
import aml.ontology.ObjectProperty;
import aml.settings.MappingRelation;
import aml.settings.MappingStatus;
import aml.util.InteractionManager;
import aml.util.MIPP;
import aml.util.Pair;

public class RepairerGraph implements Flagger
{
	
//Attributes
	
	private AML aml;
	//private RepairMap rMap;
	//private RepairMapRefine rMap;
	private RepairMapGraph rMap;
	private InteractionManager im;
	private Vector<Mapping> maps;
	private ArrayList<Mapping> wantMappings;
	private ArrayList<Mapping> unwantMappings;
	
	public int approveNum,rejectNum,iteration;
	
//Constructors
	
	/**
	 * Constructs a Repairer for automatic repair
	 */
	public RepairerGraph()
	{
		aml = AML.getInstance();
		rMap = aml.getRepairGraph();
		if(rMap == null)
			rMap = aml.buildRepairMapGraph();
		im = aml.getInteractionManager();
		maps=aml.getAlignment().getMappingSet();
		wantMappings =new ArrayList<Mapping>();
		unwantMappings=new ArrayList<Mapping>();
		
	}
	
	public void One2OneRevisedMappings()
	{
		System.out.println("Repairing Alignment");
		approveNum=0;
		rejectNum=0;
		iteration=0;		
		while(true)
		{
			System.out.println("The iteration is "+(iteration++));
			int mappingIndex = randomSelect(); //在未标记的数据中随机抽取
			if(mappingIndex != -1)
			{			
				Scanner sc = new Scanner(System.in);  
				System.out.println("当前mapping为：\n"+maps.get(mappingIndex).toString());
				//打印mapping的context信息
				printDetailInformation(maps.get(mappingIndex));
		        System.out.println("输入您的判断(Y/N):");  
		        if(sc.nextLine().equalsIgnoreCase("Y"))
		        { 	   		        	
		            System.out.println("执行专家认同的操作");  
		            rMap.one2oneRestriction(mappingIndex,wantMappings,unwantMappings);	
		            approveNum++;
		        }
		        else //后面再扩展成unknown的情况
		        {  
		        	System.out.println("执行专家拒绝操作"); 
		        	System.out.println("The mapping needs to be removed.");
					rMap.simpleReject(mappingIndex,wantMappings,unwantMappings);	  //若将此局注销，则回归到Christian’ Tool的方法，即拒绝无影响
					rejectNum++;
		        } 				
			}
			else //已经不存在错误的mapping了
			{
				break;
			}
		}
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The manual mapping revision is finished! ");
		HashSet<Integer> filterSet=new HashSet<>();
		int approvedNum=0,rejectedNum=0;
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The approved mappings are listed as followed:");
		Vector<Mapping> tempMappings=new Vector<Mapping>();
		for(Mapping m: wantMappings)
		{	
			int source=m.getSourceId();
			int target=m.getTargetId();
			int index=aml.getAlignment().getIndexBidirectional(source,target);
			if(!filterSet.contains(index))
			{
				System.out.println(m.toString());
				tempMappings.add(m);
				approvedNum++;
			}
			if(aml.getAlignment().getPropertyMap().containsKey(index))
				filterSet.addAll(aml.getAlignment().getPropertyMap().get(index));
		}	
		if(wantMappings.isEmpty())
			System.out.println("None");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The rejected mappings are listed as followed:");		
		for(Mapping m:unwantMappings)
		{
			int source=m.getSourceId();
			int target=m.getTargetId();
			int index=aml.getAlignment().getIndexBidirectional(source,target);
			if(!filterSet.contains(index))
			{
				System.out.println(m.toString());
				tempMappings.add(m);
				rejectedNum++;
			}
			if(aml.getAlignment().getPropertyMap().containsKey(index))
				filterSet.addAll(aml.getAlignment().getPropertyMap().get(index));
		}
		if(unwantMappings.isEmpty())
			System.out.println("None");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The number of original mappings is "+ (approvedNum+rejectedNum));
		System.out.println("The number of repaired mappings is "+ approveNum);
		System.out.print("Your make "+ (approveNum+rejectNum) +" decisions."+" Approve: "+approveNum +" Reject: "+ rejectNum);		
		double saving=1-(approveNum+rejectNum)*1.0/(approvedNum+rejectedNum);
		BigDecimal b1 = new BigDecimal(saving);  
		saving = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue()*100;	
		System.out.print(" Saving: "+ String.format("%1$.1f", saving)+"%\n");
		aml.removeIncorrect();		
		maps=tempMappings;
		//主要是为了刷新角色所引起的多余mappings集合
		aml.getAlignment().getMappingSet().retainAll(tempMappings);
	}
	
	public void ChristianToolRevisedMappings()
	{
		System.out.println("Repairing Alignment");
		approveNum=0;
		rejectNum=0;
		iteration=0;	
		while(true)
		{
			System.out.println("The iteration is "+(iteration++));
			int mappingIndex = getCandidateMappingByStructure(); //Christian’ Tool的原始的优化方案
			if(mappingIndex != -1)
			{			
				Scanner sc = new Scanner(System.in);  
				System.out.println("当前mapping为：\n"+maps.get(mappingIndex).toString());
				//打印mapping的context信息
				printDetailInformation(maps.get(mappingIndex));
		        System.out.println("输入您的判断(Y/N):");  
		        if(sc.nextLine().equalsIgnoreCase("Y"))
		        { 	   		        	
		            System.out.println("执行专家认同的操作");  
		            rMap.entailBasedApprove(mappingIndex,wantMappings,unwantMappings); //Christian’ Tool的支持方法 修正版本
		            approveNum++;
		        }
		        else //后面再扩展成unknown的情况
		        {  
		        	System.out.println("执行专家拒绝操作"); 
		        	System.out.println("The mapping needs to be removed.");
					rMap.simpleReject(mappingIndex,wantMappings,unwantMappings); //即拒绝无影响
					rejectNum++;
		        } 				
			}
			else //已经不存在错误的mapping了
			{
				break;
			}
		}
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The manual mapping revision is finished! ");
		HashSet<Integer> filterSet=new HashSet<>();
		int approvedNum=0,rejectedNum=0;
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The approved mappings are listed as followed:");
		Vector<Mapping> tempMappings=new Vector<Mapping>();
		for(Mapping m: wantMappings)
		{	
			int source=m.getSourceId();
			int target=m.getTargetId();
			int index=aml.getAlignment().getIndexBidirectional(source,target);
			if(!filterSet.contains(index))
			{
				System.out.println(m.toString());
				tempMappings.add(m);
				approvedNum++;
			}
			if(aml.getAlignment().getPropertyMap().containsKey(index))
				filterSet.addAll(aml.getAlignment().getPropertyMap().get(index));
		}	
		if(wantMappings.isEmpty())
			System.out.println("None");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The rejected mappings are listed as followed:");		
		for(Mapping m:unwantMappings)
		{
			int source=m.getSourceId();
			int target=m.getTargetId();
			int index=aml.getAlignment().getIndexBidirectional(source,target);
			if(!filterSet.contains(index))
			{
				System.out.println(m.toString());
				tempMappings.add(m);
				rejectedNum++;
			}
			if(aml.getAlignment().getPropertyMap().containsKey(index))
				filterSet.addAll(aml.getAlignment().getPropertyMap().get(index));
		}
		if(unwantMappings.isEmpty())
			System.out.println("None");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The number of original mappings is "+ (approvedNum+rejectedNum));
		System.out.println("The number of repaired mappings is "+ approveNum);
		System.out.print("Your make "+ (approveNum+rejectNum) +" decisions."+" Approve: "+approveNum +" Reject: "+ rejectNum);		
		double saving=1-(approveNum+rejectNum)*1.0/(approvedNum+rejectedNum);
		BigDecimal b1 = new BigDecimal(saving);  
		saving = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue()*100;	
		System.out.print(" Saving: "+ String.format("%1$.1f", saving)+"%\n");
		aml.removeIncorrect();		
		maps=tempMappings;
		//主要是为了刷新角色所引起的多余mappings集合
		aml.getAlignment().getMappingSet().retainAll(tempMappings);
	}

	public void StrongRevisedMappingsCompleteByStrutureFunction()
	{
		System.out.println("Repairing Alignment");		
		approveNum=0;
		rejectNum=0;
		iteration=0;		
		while(true)
		{
			System.out.println("The iteration is "+(iteration++));		
			int mappingIndex = getCandidateMappingByStructure(); //Christian’ Tool的原始的优化方案
			if(mappingIndex != -1)
			{			
				Scanner sc = new Scanner(System.in);  
				System.out.println("当前mapping为：\n"+maps.get(mappingIndex).toString());
				//打印mapping的context信息
				printDetailInformation(maps.get(mappingIndex));
		        System.out.println("输入您的判断(Y/N):");  
		        if(sc.nextLine().equalsIgnoreCase("Y"))
		        { 	   		        	
		            System.out.println("执行专家认同的操作");  
		            rMap.strongApproveComplete(mappingIndex,wantMappings,unwantMappings);	
		            approveNum++;
		        }
		        else //后面再扩展成unknown的情况
		        {  
		        	System.out.println("执行专家拒绝操作"); 
		        	System.out.println("The mapping needs to be removed.");
					rMap.strongRejectComplete(mappingIndex,wantMappings,unwantMappings);			
					rejectNum++;
		        } 				
			}
			else //已经不存在错误的mapping了
			{
				break;
			}
			//
		}
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The manual mapping revision is finished! ");
		HashSet<Integer> filterSet=new HashSet<>();
		int approvedNum=0,rejectedNum=0;
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The approved mappings are listed as followed:");
		Vector<Mapping> tempMappings=new Vector<Mapping>();
		for(Mapping m: wantMappings)
		{	
			int source=m.getSourceId();
			int target=m.getTargetId();
			int index=aml.getAlignment().getIndexBidirectional(source,target);
			if(!filterSet.contains(index))
			{
				System.out.println(m.toString());
				tempMappings.add(m);
				approvedNum++;
			}
			if(aml.getAlignment().getPropertyMap().containsKey(index))
				filterSet.addAll(aml.getAlignment().getPropertyMap().get(index));
		}	
		if(wantMappings.isEmpty())
			System.out.println("None");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The rejected mappings are listed as followed:");		
		for(Mapping m:unwantMappings)
		{
			int source=m.getSourceId();
			int target=m.getTargetId();
			int index=aml.getAlignment().getIndexBidirectional(source,target);
			if(!filterSet.contains(index))
			{
				System.out.println(m.toString());
				tempMappings.add(m);
				rejectedNum++;
			}
			if(aml.getAlignment().getPropertyMap().containsKey(index))
				filterSet.addAll(aml.getAlignment().getPropertyMap().get(index));
		}
		if(unwantMappings.isEmpty())
			System.out.println("None");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The number of original mappings is "+ (approvedNum+rejectedNum));
		System.out.println("The number of repaired mappings is "+ approveNum);
		System.out.print("Your make "+ (approveNum+rejectNum) +" decisions."+" Approve: "+approveNum +" Reject: "+ rejectNum);		
		double saving=1-(approveNum+rejectNum)*1.0/(approvedNum+rejectedNum);
		BigDecimal b1 = new BigDecimal(saving);  
		saving = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue()*100;	
		System.out.print(" Saving: "+ String.format("%1$.1f", saving)+"%\n");
		aml.removeIncorrect();		
		maps=tempMappings;
		//主要是为了刷新角色所引起的多余mappings集合
		aml.getAlignment().getMappingSet().retainAll(tempMappings);
	}
	
	public void StrongRevisedMappingsCompleteByOptimalFunction()
	{
		System.out.println("Repairing Alignment");		
		approveNum=0;
		rejectNum=0;
		iteration=0;		
		while(true)
		{
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("The iteration is "+(iteration++));
			//int mappingIndex = getCandidateMapping2(); //将权重最大的抽取出来	
			//int mappingIndex = randomSelect(); //在未标记的数据中随机抽取	
			int mappingIndex = getCandidateMappingByImpactor(); //基于impactor factor的定义来进行抽取
			if(mappingIndex != -1)
			{			
				Scanner sc = new Scanner(System.in);  
				System.out.println("The current mapping is：\n"+maps.get(mappingIndex).toString());
				//打印mapping的context信息
				printDetailInformation(maps.get(mappingIndex));
		        System.out.println("Please enter your judgment(Y/N):");  
		        if(sc.nextLine().equalsIgnoreCase("Y"))
		        { 	   		        	
		            System.out.println("Execute the action according to the approved mapping.");  
		            rMap.strongApproveComplete(mappingIndex,wantMappings,unwantMappings);	
		            approveNum++;
		        }
		        else //后面再扩展成unknown的情况
		        {  
		        	System.out.println("Execute the action according to the rejected mapping."); 
		        	System.out.println("The mapping needs to be removed.");
					rMap.strongRejectComplete(mappingIndex,wantMappings,unwantMappings);			
					rejectNum++;
		        } 				
			}
			else //已经不存在错误的mapping了
			{
				break;
			}
			//
		}
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The manual mapping revision is finished! ");
		HashSet<Integer> filterSet=new HashSet<>();
		int approvedNum=0,rejectedNum=0;
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The approved mappings are listed as followed:");
		Vector<Mapping> tempMappings=new Vector<Mapping>();
		for(Mapping m: wantMappings)
		{	
			int source=m.getSourceId();
			int target=m.getTargetId();
			int index=aml.getAlignment().getIndexBidirectional(source,target);
			if(!filterSet.contains(index))
			{
				System.out.println(m.toString());
				tempMappings.add(m);
				approvedNum++;
			}
			if(aml.getAlignment().getPropertyMap().containsKey(index))
				filterSet.addAll(aml.getAlignment().getPropertyMap().get(index));
		}	
		if(wantMappings.isEmpty())
			System.out.println("None");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The rejected mappings are listed as followed:");		
		for(Mapping m:unwantMappings)
		{
			int source=m.getSourceId();
			int target=m.getTargetId();
			int index=aml.getAlignment().getIndexBidirectional(source,target);
			if(!filterSet.contains(index))
			{
				System.out.println(m.toString());
				tempMappings.add(m);
				rejectedNum++;
			}
			if(aml.getAlignment().getPropertyMap().containsKey(index))
				filterSet.addAll(aml.getAlignment().getPropertyMap().get(index));
		}
		if(unwantMappings.isEmpty())
			System.out.println("None");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("The number of original mappings is "+ (approvedNum+rejectedNum));
		System.out.println("The number of repaired mappings is "+ approveNum);
		System.out.print("Your make "+ (approveNum+rejectNum) +" decisions."+" Approve: "+approveNum +" Reject: "+ rejectNum);		
		double saving=1-(approveNum+rejectNum)*1.0/(approvedNum+rejectedNum);
		BigDecimal b1 = new BigDecimal(saving);  
		saving = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue()*100;	
		System.out.print(" Saving: "+ String.format("%1$.1f", saving)+"%\n");
		aml.removeIncorrect();		
		maps=tempMappings;
		//主要是为了刷新角色所引起的多余mappings集合
		aml.getAlignment().getMappingSet().retainAll(tempMappings);
	}
	
	@Override
	public void flag()
	{
		System.out.println("Running Coherence Flagger");
		long time = System.currentTimeMillis()/1000;
		for(Integer i : rMap)
			if(rMap.getMapping(i).getStatus().equals(MappingStatus.UNKNOWN))
				rMap.getMapping(i).setStatus(MappingStatus.FLAGGED);
		System.out.println("Finished in " +	(System.currentTimeMillis()/1000-time) + " seconds");
	}
	
	private int randomSelect() //根据MIPSs的个数与mapping的权重
	{			
		int candidateMapping = -1;	
		ArrayList<Integer> randomSet=new ArrayList<Integer>();
		for(int i=0;i<maps.size();i++)  //这里只对待评估的mappings进行排序
		{
			if(maps.get(i).getStatus().equals(MappingStatus.UNKNOWN))
			{
				int originalIndex=restoreRoleMapping(i);  //考虑到角色存在需要加上此句
				if(!randomSet.contains(originalIndex))
					randomSet.add(originalIndex);
			}
		}
		if(randomSet.isEmpty())
			return candidateMapping;	
		Random ra =new Random();
		int randomIndex=ra.nextInt(randomSet.size());
		candidateMapping=randomSet.get(randomIndex);
		return candidateMapping;
	}
	
	private int getCandidateMapping() //根据MIPSs的个数与mapping的权重
	{			
		int candidateMapping = -1;
		HashMap<Integer,Integer> CandidateMappings=new HashMap<Integer,Integer>();		
		for(int i=0;i<maps.size();i++)  //这里只对待评估的mappings进行排序
		{
			if(maps.get(i).getStatus().equals(MappingStatus.UNKNOWN))
			{
				int card=-1;
				if(rMap.getMapMinimalConflictSets().containsKey(i))
				{
				  card = rMap.getMapMinimalConflictSets().get(i).size();
				}
				else 
				{
				  card=0;
				}
				//考虑到有角色存在需要进行还原(如果是概念的话，那就是同一个索引)
				int originalIndex=restoreRoleMapping(i);
				if(CandidateMappings.containsKey(originalIndex)) //角色的情况
				{
					card=card+CandidateMappings.get(originalIndex);
					CandidateMappings.put(originalIndex, card);	
				}
				else //概念的情况
				{
					CandidateMappings.put(originalIndex, card);	
				}
			}
		}
		if(CandidateMappings.isEmpty())
			return -1;
		List<Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(CandidateMappings.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            //降序排序
            public int compare(Entry<Integer, Integer> o1,
                    Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }         
        });
        
        //先考虑MIPSs出现次数最多的mappings   
        ArrayList<Integer> CardMapping=new ArrayList<Integer>();
		int maxCard = 0;
		for(Entry<Integer, Integer> mapping:list)
		{ 
            int key=mapping.getKey();
            if(maxCard==0)
         	{
         		maxCard=mapping.getValue();
         		CardMapping.add(key);
         	}
            else if(maxCard==mapping.getValue())
        		CardMapping.add(key);
        	else 
        	{
				break;
			}     
        } 
		if(CardMapping.size()==1)
		{
			candidateMapping=CardMapping.get(0);		
		}
		else
		{			
			ArrayList<Integer> WeightsMapping=new ArrayList<Integer>();
			WeightsMapping=getminimalWeight2(CardMapping);
			candidateMapping=WeightsMapping.get(0);		
		}
		return candidateMapping;	
	}
	
	private int getCandidateMapping2()  //简单依赖权重的来排序
	{			
		int candidateMapping = -1;
		HashMap<Integer,Double> CandidateMappings=new HashMap<Integer,Double>();		
		for(int i=0;i<maps.size();i++)  //这里只对待评估的mappings进行排序
		{
			if(maps.get(i).getStatus().equals(MappingStatus.UNKNOWN))
			{		
				/*double sim = maps.get(i).getSimilarity();
				CandidateMappings.put(i, sim);*/			
				int originalIndex=restoreRoleMapping(i);
				double sim = maps.get(i).getSimilarity();
				CandidateMappings.put(originalIndex, sim);			
			}
		}
		if(CandidateMappings.isEmpty())
			return -1;
		List<Entry<Integer, Double>> list = new ArrayList<Map.Entry<Integer,Double>>(CandidateMappings.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Double>>() {
            //降序排序
            public int compare(Entry<Integer, Double> o1,
                    Entry<Integer, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }         
        });
        
        //先考虑MIPSs出现次数最多的mappings   
        ArrayList<Integer> CardMapping=new ArrayList<Integer>();
		double maxSim = 0;
		for(Entry<Integer, Double> mapping:list)
		{ 
            int key=mapping.getKey();
            if(maxSim==0)
         	{
            	maxSim=mapping.getValue();
         		CardMapping.add(key);
         	}
            else if(maxSim==mapping.getValue())
        		CardMapping.add(key);
        	else 
        	{
				break;
			}     
        } 
		
		candidateMapping=CardMapping.get(0);				
		return candidateMapping;	
	}
	
	private int getCandidateMappingByStructure()  //依靠原本体的结构来进行排序  Christian’ Tool的原始的优化方案
	{	
		int candidateMapping = -1;
		HashMap<Integer,Integer> CandidateMappings=new HashMap<Integer,Integer>();	
		for(int i=0;i<maps.size();i++)  //这里只对待评估的mappings进行排序
		{
			if(maps.get(i).getStatus().equals(MappingStatus.UNKNOWN))
			{	
				Mapping m=maps.get(i);
				int source=m.getSourceId();
				int target=m.getTargetId();
				int impactor=0;
				if(m.getRelationship().equals(MappingRelation.SUBCLASS))
				{
					Set<Integer> subClass1 = aml.getRelationshipMap().getSubClasses(source, true);
					Set<Integer> superClass2 = aml.getRelationshipMap().getSuperClasses(target, true);
					Set<Integer> disClass2 = aml.getRelationshipMap().getDisjoint(target);
					
					impactor=subClass1.size()*(superClass2.size()+disClass2.size());				
				}
				else if(m.getRelationship().equals(MappingRelation.SUPERCLASS))
				{
					Set<Integer> superClass1 = aml.getRelationshipMap().getSuperClasses(source, true);
					Set<Integer> subClass2 = aml.getRelationshipMap().getSubClasses(target, true);
					Set<Integer> disClass2 = aml.getRelationshipMap().getDisjoint(target);		
					
					impactor=superClass1.size()*(subClass2.size()+disClass2.size());
				}	
				else if(m.getRelationship().equals(MappingRelation.EQUIVALENCE))
				{
					Set<Integer> subClass1 = aml.getRelationshipMap().getSubClasses(source, true);
					Set<Integer> superClass2 = aml.getRelationshipMap().getSuperClasses(target, true);
					Set<Integer> disClass21 = aml.getRelationshipMap().getDisjoint(target);
					
					int impactor1=subClass1.size()*(superClass2.size()+disClass21.size());
					
					Set<Integer> superClass1 = aml.getRelationshipMap().getSuperClasses(source, true);
					Set<Integer> subClass2 = aml.getRelationshipMap().getSubClasses(target, true);
					Set<Integer> disClass22 = aml.getRelationshipMap().getDisjoint(target);		
					
					int impactor2=superClass1.size()*(subClass2.size()+disClass22.size());
					
					impactor=impactor1+impactor2;					
				}       
                int originalIndex=restoreRoleMapping(i);
				if(CandidateMappings.containsKey(originalIndex)) //角色的情况
				{							
					impactor=CandidateMappings.get(originalIndex); //(impactor-1是为了防止不同匿名角色的累加)
					CandidateMappings.put(originalIndex, impactor);			
				}
				else //概念的情况
				{
					CandidateMappings.put(originalIndex, impactor);	
				}		
				
			}
		}
		if(CandidateMappings.isEmpty())
			return -1;
		List<Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(CandidateMappings.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            //降序排序
            public int compare(Entry<Integer, Integer> o1,
                    Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }         
        });
              
        ArrayList<Integer> CardMapping=new ArrayList<Integer>();
		int maxCard = 0;
		for(Entry<Integer, Integer> mapping:list)
		{ 
            int key=mapping.getKey();
            if(maxCard==0)
         	{
            	maxCard=mapping.getValue();
         		CardMapping.add(key);
         	}
            else if(maxCard==mapping.getValue())
        		CardMapping.add(key);
        	else 
        	{
				break;
			}     
        } 	
		candidateMapping=CardMapping.get(0);		
		return candidateMapping;	
	}
	
	private int getCandidateMappingByStructure(ArrayList<Integer> candidateSet)  //依靠原本体的结构来进行排序  Christian’ Tool的原始的优化方案
	{	
		int candidateMapping = -1;
		HashMap<Integer,Integer> CandidateMappings=new HashMap<Integer,Integer>();	
		for(int i=0;i<candidateSet.size();i++)  //这里只对待评估的mappings进行排序
		{
			if(maps.get(candidateSet.get(i)).getStatus().equals(MappingStatus.UNKNOWN))
			{	
				Mapping m=maps.get(candidateSet.get(i));
				int source=m.getSourceId();
				int target=m.getTargetId();
				int impactor=0;
				if(m.getRelationship().equals(MappingRelation.SUBCLASS))
				{
					Set<Integer> subClass1 = aml.getRelationshipMap().getSubClasses(source, true);
					Set<Integer> superClass2 = aml.getRelationshipMap().getSuperClasses(target, true);
					Set<Integer> disClass2 = aml.getRelationshipMap().getDisjoint(target);
					
					impactor=subClass1.size()*(superClass2.size()+disClass2.size());				
				}
				else if(m.getRelationship().equals(MappingRelation.SUPERCLASS))
				{
					Set<Integer> superClass1 = aml.getRelationshipMap().getSuperClasses(source, true);
					Set<Integer> subClass2 = aml.getRelationshipMap().getSubClasses(target, true);
					Set<Integer> disClass2 = aml.getRelationshipMap().getDisjoint(target);		
					
					impactor=superClass1.size()*(subClass2.size()+disClass2.size());
				}	
				else if(m.getRelationship().equals(MappingRelation.EQUIVALENCE))
				{
					Set<Integer> subClass1 = aml.getRelationshipMap().getSubClasses(source, true);
					Set<Integer> superClass2 = aml.getRelationshipMap().getSuperClasses(target, true);
					Set<Integer> disClass21 = aml.getRelationshipMap().getDisjoint(target);
					
					int impactor1=subClass1.size()*(superClass2.size()+disClass21.size());
					
					Set<Integer> superClass1 = aml.getRelationshipMap().getSuperClasses(source, true);
					Set<Integer> subClass2 = aml.getRelationshipMap().getSubClasses(target, true);
					Set<Integer> disClass22 = aml.getRelationshipMap().getDisjoint(target);		
					
					int impactor2=superClass1.size()*(subClass2.size()+disClass22.size());
					
					impactor=impactor1+impactor2;					
				}       
                int originalIndex=restoreRoleMapping(candidateSet.get(i));
				if(CandidateMappings.containsKey(originalIndex)) //角色的情况
				{							
					impactor=CandidateMappings.get(originalIndex); //(impactor-1是为了防止不同匿名角色的累加)
					CandidateMappings.put(originalIndex, impactor);			
				}
				else //概念的情况
				{
					CandidateMappings.put(originalIndex, impactor);	
				}		
				
			}
		}
		if(CandidateMappings.isEmpty())
			return -1;
		List<Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(CandidateMappings.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            //降序排序
            public int compare(Entry<Integer, Integer> o1,
                    Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }         
        });
              
        ArrayList<Integer> CardMapping=new ArrayList<Integer>();
		int maxCard = 0;
		for(Entry<Integer, Integer> mapping:list)
		{ 
            int key=mapping.getKey();
            if(maxCard==0)
         	{
            	maxCard=mapping.getValue();
         		CardMapping.add(key);
         	}
            else if(maxCard==mapping.getValue())
        		CardMapping.add(key);
        	else 
        	{
				break;
			}     
        } 	
		candidateMapping=CardMapping.get(0);		
		return candidateMapping;	
	}
	
	private int getCandidateMappingByImpactor()  //简单依赖权重的来排序
	{			
		int candidateMapping = -1;
		HashMap<Integer,Integer> CandidateMappings=new HashMap<Integer,Integer>();	
		HashMap<Integer,Boolean> mappingJudge=new HashMap<Integer,Boolean>();
		//int maxImpactor=0;
		for(int i=0;i<maps.size();i++)  //这里只对待评估的mappings进行排序
		{
			Boolean flag=true; //true表示赞同，false表示拒绝
			if(maps.get(i).getStatus().equals(MappingStatus.UNKNOWN))
			{		
				//System.out.println(i);
				//System.out.println(maps.get(i).toString());
				int impactPlus=obtainImpactPlus(i); //获取正面影响的值
				//int impactConflict=obtainImpactConflict(i); //获取冲突影响的值
				int impactConflict=obtainImpactConflict2(i); //获取冲突影响的值
				int impactMinor=obtainImpactMinor(i);   //获取反面影响的值 
                int impactor=Math.max(impactPlus+impactConflict, impactMinor);
                if((impactPlus+impactConflict)<impactMinor) //默认是拒绝的情况
                	flag=false;         
                int originalIndex=restoreRoleMapping(i);
				if(CandidateMappings.containsKey(originalIndex)) //角色的情况
				{
					
					boolean tempflag=mappingJudge.get(originalIndex);
					if(tempflag==flag) //目标一致,即均为赞同或者拒绝
					{					
						impactor=(impactor-1)+CandidateMappings.get(originalIndex); //(impactor-1是为了防止不同匿名角色的累加)
						CandidateMappings.put(originalIndex, impactor);
						mappingJudge.put(originalIndex, flag);
					}
					else 
					{
						int tempImpactor=CandidateMappings.get(originalIndex);
						if(tempImpactor<=impactor) //原始的情况比当前情况影响因子更大,保留原始的方案
						{
							CandidateMappings.put(originalIndex, impactor);
							mappingJudge.put(originalIndex, flag);
							
						}
					}			
				}
				else //概念的情况
				{
					CandidateMappings.put(originalIndex, impactor);	
					mappingJudge.put(originalIndex, flag);
				}		
				
				mappingJudge.put(originalIndex, flag);
			}
		}
		if(CandidateMappings.isEmpty())
			return -1;
		List<Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(CandidateMappings.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            //降序排序
            public int compare(Entry<Integer, Integer> o1,
                    Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }         
        });
              
        ArrayList<Integer> CardMapping=new ArrayList<Integer>();
		int maxCard = 0;
		for(Entry<Integer, Integer> mapping:list)
		{ 
            int key=mapping.getKey();
            if(maxCard==0)
         	{
            	maxCard=mapping.getValue();
         		CardMapping.add(key);
         	}
            else if(maxCard==mapping.getValue())
        		CardMapping.add(key);
        	else 
        	{
				break;
			}     
        } 
		
		if(CardMapping.size()==1)
		{
			candidateMapping=CardMapping.get(0);		
		}
		else
		{
			Boolean judge=mappingJudge.get(CardMapping.get(0)); //或者赞同还是拒绝的趋势
			ArrayList<Integer> WeightsMapping=new ArrayList<Integer>();
			WeightsMapping=selectCandidateMappingByWeight(CardMapping,judge); //根据拒绝还是赞同还觉得排序的方式,赞同则是降序，拒绝则是升序		
			candidateMapping=WeightsMapping.get(0);		
		}		
		return candidateMapping;	
	}
	
	private int getCandidateMappingByImpactor(ArrayList<Integer> candidates)  //简单依赖权重的来排序
	{			
		int candidateMapping = -1;
		HashMap<Integer,Integer> CandidateMappings=new HashMap<Integer,Integer>();	
		HashMap<Integer,Boolean> mappingJudge=new HashMap<Integer,Boolean>();
		//int maxImpactor=0;
		for(int i=0;i<candidates.size();i++)  //这里只对待评估的mappings进行排序
		{
			int index=candidates.get(i);
			Boolean flag=true; //true表示赞同，false表示拒绝
			if(maps.get(index).getStatus().equals(MappingStatus.UNKNOWN))
			{		
				
				int impactPlus=obtainImpactPlus(index); //获取正面影响的值
				//int impactConflict=obtainImpactConflict(i); //获取冲突影响的值
				int impactConflict=obtainImpactConflict2(index); //获取冲突影响的值
				int impactMinor=obtainImpactMinor(index);   //获取反面影响的值 
                int impactor=Math.max(impactPlus+impactConflict, impactMinor);
                if((impactPlus+impactConflict)<impactMinor) //默认是拒绝的情况
                	flag=false;         
                int originalIndex=restoreRoleMapping(index);
				if(CandidateMappings.containsKey(originalIndex)) //角色的情况
				{
					
					boolean tempflag=mappingJudge.get(originalIndex);
					if(tempflag==flag) //目标一致,即均为赞同或者拒绝
					{					
						impactor=(impactor-1)+CandidateMappings.get(originalIndex); //(impactor-1是为了防止不同匿名角色的累加)
						CandidateMappings.put(originalIndex, impactor);
						mappingJudge.put(originalIndex, flag);
					}
					else 
					{
						int tempImpactor=CandidateMappings.get(originalIndex);
						if(tempImpactor<=impactor) //原始的情况比当前情况影响因子更大,保留原始的方案
						{
							CandidateMappings.put(originalIndex, impactor);
							mappingJudge.put(originalIndex, flag);
							
						}
					}			
				}
				else //概念的情况
				{
					CandidateMappings.put(originalIndex, impactor);	
					mappingJudge.put(originalIndex, flag);
				}		
				
				mappingJudge.put(originalIndex, flag);
			}
		}
		if(CandidateMappings.isEmpty())
			return -1;
		List<Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(CandidateMappings.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            //降序排序
            public int compare(Entry<Integer, Integer> o1,
                    Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }         
        });
              
        ArrayList<Integer> CardMapping=new ArrayList<Integer>();
		int maxCard = 0;
		for(Entry<Integer, Integer> mapping:list)
		{ 
            int key=mapping.getKey();
            if(maxCard==0)
         	{
            	maxCard=mapping.getValue();
         		CardMapping.add(key);
         	}
            else if(maxCard==mapping.getValue())
        		CardMapping.add(key);
        	else 
        	{
				break;
			}     
        } 
		
		if(CardMapping.size()==1)
		{
			candidateMapping=CardMapping.get(0);		
		}
		else
		{
			Boolean judge=mappingJudge.get(CardMapping.get(0)); //或者赞同还是拒绝的趋势
			ArrayList<Integer> WeightsMapping=new ArrayList<Integer>();
			WeightsMapping=selectCandidateMappingByWeight(CardMapping,judge); //根据拒绝还是赞同还觉得排序的方式,赞同则是降序，拒绝则是升序		
			candidateMapping=WeightsMapping.get(0);		
		}		
		return candidateMapping;	
	}
	
	private int getCandidateMappingByStructureAndImpactor()  //简单依赖权重的来排序
	{			
		int candidateMapping = -1;
		HashMap<Integer,Integer> CandidateMappings=new HashMap<Integer,Integer>();	
		for(int i=0;i<maps.size();i++)  //这里只对待评估的mappings进行排序
		{
			if(maps.get(i).getStatus().equals(MappingStatus.UNKNOWN))
			{	
				Mapping m=maps.get(i);
				int source=m.getSourceId();
				int target=m.getTargetId();
				int impactor=0;
				if(m.getRelationship().equals(MappingRelation.SUBCLASS))
				{
					Set<Integer> subClass1 = aml.getRelationshipMap().getSubClasses(source, true);
					Set<Integer> superClass2 = aml.getRelationshipMap().getSuperClasses(target, true);
					Set<Integer> disClass2 = aml.getRelationshipMap().getDisjoint(target);
					
					impactor=subClass1.size()*(superClass2.size()+disClass2.size());				
				}
				else if(m.getRelationship().equals(MappingRelation.SUPERCLASS))
				{
					Set<Integer> superClass1 = aml.getRelationshipMap().getSuperClasses(source, true);
					Set<Integer> subClass2 = aml.getRelationshipMap().getSubClasses(target, true);
					Set<Integer> disClass2 = aml.getRelationshipMap().getDisjoint(target);		
					
					impactor=superClass1.size()*(subClass2.size()+disClass2.size());
				}	
				else if(m.getRelationship().equals(MappingRelation.EQUIVALENCE))
				{
					Set<Integer> subClass1 = aml.getRelationshipMap().getSubClasses(source, true);
					Set<Integer> superClass2 = aml.getRelationshipMap().getSuperClasses(target, true);
					Set<Integer> disClass21 = aml.getRelationshipMap().getDisjoint(target);
					
					int impactor1=subClass1.size()*(superClass2.size()+disClass21.size());
					
					Set<Integer> superClass1 = aml.getRelationshipMap().getSuperClasses(source, true);
					Set<Integer> subClass2 = aml.getRelationshipMap().getSubClasses(target, true);
					Set<Integer> disClass22 = aml.getRelationshipMap().getDisjoint(target);		
					
					int impactor2=superClass1.size()*(subClass2.size()+disClass22.size());
					
					impactor=impactor1+impactor2;					
				}       
                int originalIndex=restoreRoleMapping(i);
				if(CandidateMappings.containsKey(originalIndex)) //角色的情况
				{							
					impactor=CandidateMappings.get(originalIndex); //(impactor-1是为了防止不同匿名角色的累加)
					CandidateMappings.put(originalIndex, impactor);			
				}
				else //概念的情况
				{
					CandidateMappings.put(originalIndex, impactor);	
				}		
				
			}
		}
		if(CandidateMappings.isEmpty())
			return -1;
		List<Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(CandidateMappings.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            //降序排序
            public int compare(Entry<Integer, Integer> o1,
                    Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }         
        });
              
        ArrayList<Integer> CardMapping=new ArrayList<Integer>();
		int maxCard = 0;
		for(Entry<Integer, Integer> mapping:list)
		{ 
            int key=mapping.getKey();
            if(maxCard==0)
         	{
            	maxCard=mapping.getValue();
         		CardMapping.add(key);
         	}
            else if(maxCard==mapping.getValue())
        		CardMapping.add(key);
        	else 
        	{
				break;
			}     
        } 	
		
		if(CardMapping.size()==1)
		{
			candidateMapping=CardMapping.get(0);		
		}
		else
		{
			candidateMapping=getCandidateMappingByImpactor(CardMapping);	
		}			
		return candidateMapping;	
	}
	
	
	
	
	private int getCandidateMappingByVadidityRatio()  //根据JWS2012的的impactor函数进行排序
	{				
		double validatityRatio=0.75;
		if(iteration!=1) //根据判断的情况进行自动调节
			validatityRatio=wantMappings.size()*1.0/(wantMappings.size()+unwantMappings.size());		
		int candidateMapping = -1;
		HashMap<Integer,Double> CandidateMappings=new HashMap<Integer,Double>();	
		double sum=0;
		for(int i=0;i<maps.size();i++)  //这里只对待评估的mappings进行排序
		{
			if(maps.get(i).getStatus().equals(MappingStatus.UNKNOWN))
				sum++;
		}
			
		for(int i=0;i<maps.size();i++)  //这里只对待评估的mappings进行排序
		{
			if(maps.get(i).getStatus().equals(MappingStatus.UNKNOWN))
			{					
				double impactPlus=obtainImpactPlus(i)/sum; //获取正面影响的值
				double impactConflict=obtainImpactConflict(i)/sum; //获取冲突影响的值
				double impactMinor=obtainImpactMinor(i)/sum;   //获取反面影响的值 
               
				//获取标准化的值并且四舍五入
				double normImpactPlus=-Math.abs(validatityRatio-impactPlus);
				BigDecimal b1 = new BigDecimal(normImpactPlus);  
				normImpactPlus = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue(); 
				double normImpactConflict=-Math.abs(1-validatityRatio-impactConflict);
				BigDecimal b2 = new BigDecimal(normImpactConflict);  
				normImpactConflict = b2.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue(); 
				double normImpactMinor=-Math.abs(1-validatityRatio-impactMinor);
				BigDecimal b3 = new BigDecimal(normImpactMinor);  
				normImpactMinor = b3.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue(); 
			
				//norm的是一种偏差的大小的衡量
				double norm=Math.max(normImpactPlus, Math.max(normImpactConflict, normImpactMinor));
							
				CandidateMappings.put(i, norm);	
			}
		}
		if(CandidateMappings.isEmpty())
			return -1;
		List<Entry<Integer, Double>> list = new ArrayList<Map.Entry<Integer,Double>>(CandidateMappings.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Double>>() {
            //降序排序
            public int compare(Entry<Integer, Double> o1,
                    Entry<Integer, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }         
        });
        
        
        ArrayList<Integer> impactorMapping=new ArrayList<Integer>();
		double impactor = 0;
		for(Entry<Integer, Double> mapping:list)
		{ 
            int key=mapping.getKey();
            if(impactor==0)
         	{
            	impactor=mapping.getValue();
            	impactorMapping.add(key);
         	}
            else if(impactor==mapping.getValue())
            	impactorMapping.add(key);
        	else 
        	{
				break;
			}     
        } 
		
		//是否考虑用相似度进行扩展(但积极的还是消极的还是不太好说)
		candidateMapping=impactorMapping.get(0);		
		return candidateMapping;	
	}
	
	private int getCandidateMappingByVadidityRatio2()  //根据JWS2012的的impactor函数进行排序
	{				
		double validatityRatio=0.5;
		HashMap<Integer,Boolean> mappingJudge=new HashMap<Integer,Boolean>();
		if(iteration!=1) //根据判断的情况进行自动调节
			validatityRatio=wantMappings.size()*1.0/(wantMappings.size()+unwantMappings.size());		
		int candidateMapping = -1;
		HashMap<Integer,Double> CandidateMappings=new HashMap<Integer,Double>();	
		double sum=0;
		for(int i=0;i<maps.size();i++)  //这里只对待评估的mappings进行排序
		{
			if(maps.get(i).getStatus().equals(MappingStatus.UNKNOWN))
				sum++;
		}
		
		//正向偏差与反向偏差
		for(int i=0;i<maps.size();i++)  //这里只对待评估的mappings进行排序
		{
			boolean flag=true;
			if(maps.get(i).getStatus().equals(MappingStatus.UNKNOWN))
			{					
				double impactPlus=obtainImpactPlus(i)/sum; //获取正面影响的值
				double impactConflict=obtainImpactConflict(i)/sum; //获取冲突影响的值
				double impactMinor=obtainImpactMinor(i)/sum;   //获取反面影响的值 
               
				//获取标准化的值并且四舍五入
				double normImpactPlus=-Math.abs(validatityRatio-impactPlus);
				BigDecimal b1 = new BigDecimal(normImpactPlus);  
				normImpactPlus = b1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue(); 
				double normImpactConflict=-Math.abs(1-validatityRatio-impactConflict);
				BigDecimal b2 = new BigDecimal(normImpactConflict);  
				normImpactConflict = b2.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue(); 
				double normImpactMinor=-Math.abs(1-validatityRatio-impactMinor);
				BigDecimal b3 = new BigDecimal(normImpactMinor);  
				normImpactMinor = b3.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue(); 
			
				//norm的是一种偏差的大小的衡量
				double norm=Math.max(normImpactPlus, Math.max(normImpactConflict, normImpactMinor));
				if(norm==normImpactPlus||norm==normImpactConflict)
					flag=true;
				else
					flag=false;
							
				CandidateMappings.put(i, norm);	
				mappingJudge.put(i, flag);
			}
		}
		if(CandidateMappings.isEmpty())
			return -1;
		List<Entry<Integer, Double>> list = new ArrayList<Map.Entry<Integer,Double>>(CandidateMappings.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Double>>() {
            //降序排序
            public int compare(Entry<Integer, Double> o1,
                    Entry<Integer, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }         
        });
        
        
        ArrayList<Integer> impactorMapping=new ArrayList<Integer>();
		double impactor = 0;
		for(Entry<Integer, Double> mapping:list)
		{ 
            int key=mapping.getKey();
            if(impactor==0)
         	{
            	impactor=mapping.getValue();
            	impactorMapping.add(key);
         	}
            else if(impactor==mapping.getValue())
            	impactorMapping.add(key);
        	else 
        	{
				break;
			}     
        } 
		
		//是否考虑用相似度进行扩展(但积极的还是消极的还是不太好说)
		//candidateMapping=impactorMapping.get(0);	
		if(impactorMapping.size()==1)
		{
			candidateMapping=impactorMapping.get(0);		
		}
		else
		{
			Boolean judge=mappingJudge.get(impactorMapping.get(0)); //或者赞同还是拒绝的趋势
			ArrayList<Integer> WeightsMapping=new ArrayList<Integer>();
			WeightsMapping=selectCandidateMappingByWeight(impactorMapping,judge); //根据拒绝还是赞同还觉得排序的方式,赞同则是降序，拒绝则是升序		
			candidateMapping=WeightsMapping.get(0);		
		}		
		return candidateMapping;		
	}
	

	/*private int getIncorrectMapping()
	{			
		int worstMapping = -1;
		int maxCard = 0;	
		HashMap<Integer,ArrayList<Integer>> CardMapping=new HashMap<Integer,ArrayList<Integer>>();
		for(Integer map: rMap.getMapMinimalConflictSets().keySet())
		{		
			int card = rMap.getMapMinimalConflictSets().get(map).size();
			if(card > maxCard )			
			{
				maxCard = card;			
			}
			if(CardMapping.keySet().contains(card))
			{
				CardMapping.get(card).add(map);
			}
			else
			{
				ArrayList<Integer> list=new ArrayList<Integer>();
				list.add(map);
				CardMapping.put(card, list);
			}
		}
		if(maxCard==0) //no MIPPs
			return -1;
			
		ArrayList<Integer> maxCardMapping=new ArrayList<Integer>();
		maxCardMapping=CardMapping.get(maxCard);
		if(maxCardMapping.size()==1)
		{
			worstMapping=maxCardMapping.get(0);		
		}
		else
		{
			ArrayList<Integer> commonEntailment=new ArrayList<Integer>();
			commonEntailment=getMinimalEntailment(maxCardMapping);
			if(commonEntailment.size()==1)
				worstMapping=commonEntailment.get(0);
			else
			{
				ArrayList<Integer> WeightsMapping=new ArrayList<Integer>();
				WeightsMapping=getminimalWeight(commonEntailment);
				worstMapping=WeightsMapping.get(0);
			}
		}
		return worstMapping;
	}*/
	
	private int getIncorrectMapping2()
	{	
		int worstMapping = -1;
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		for(Integer mappings: rMap.getMapMinimalConflictSets().keySet())
		{
			map.put(mappings,rMap.getMapMinimalConflictSets().get(mappings).size());		
		}
		
		List<Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            //降序排序
            public int compare(Entry<Integer, Integer> o1,
                    Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }         
        });
		
		ArrayList<Integer> CardMapping=new ArrayList<Integer>();
		int maxCard = 0;
		 for(Entry<Integer, Integer> mapping:list)
		{ 
            int key=mapping.getKey();
            if(maxCard==0)
         	{
         		maxCard=mapping.getValue();
         		CardMapping.add(key);
         	}
            else if(maxCard==mapping.getValue())
        		CardMapping.add(key);
        	else 
        	{
				break;
			}     
        } 
		if(maxCard==0) //no MIPPs
			return -1;
		if(CardMapping.size()==1)
		{
			worstMapping=CardMapping.get(0);		
		}
		else
		{
			ArrayList<Integer> commonEntailment=new ArrayList<Integer>();
			commonEntailment=getMinimalEntailment2(CardMapping);
			if(commonEntailment.size()==1)
				worstMapping=commonEntailment.get(0);
			else
			{
				ArrayList<Integer> WeightsMapping=new ArrayList<Integer>();
				WeightsMapping=getminimalWeight2(commonEntailment);
				worstMapping=WeightsMapping.get(0);
			}
		}
		return worstMapping;
	}
	
	/*private ArrayList<Integer> getMinimalEntailment(ArrayList<Integer> maxCardMapping) 
	{
		int commonNum=Integer.MAX_VALUE;
		HashMap<Integer,ArrayList<Integer>> CommonMapping=new HashMap<Integer,ArrayList<Integer>>();
		for(Integer map:maxCardMapping)
		{
			int num=0;
			for(Pair<ArrayList<Integer>, ArrayList<Integer>> mipp:rMap.getMapMinimalConflictSets().get(map))
			{
				//HashMap<Path, Set<Integer>> commonEntailment=getCommonEntailment(mipp);
				//HashMap<Path, Set<Integer>> commonEntailment=mipp.getCommonEntailment();
				for(Path path:commonEntailment.keySet())
				{
					if(path.contains(map))
						num=num+commonEntailment.get(path).size();
				}
			}
			if(commonNum>num)
			{
				commonNum=num;
			}
			if(CommonMapping.keySet().contains(num))
			{
				CommonMapping.get(num).add(map);
			}
			else
			{
				ArrayList<Integer> list=new ArrayList<Integer>();
				list.add(map);
				CommonMapping.put(num, list);
			}			
		}
		ArrayList<Integer> commonEntailmentMappping=new ArrayList<Integer>();
		commonEntailmentMappping=CommonMapping.get(commonNum);
		return commonEntailmentMappping;
	}*/
	
	private ArrayList<Integer> getMinimalEntailment2(ArrayList<Integer> maxCardMapping) 
	{
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();	
		for(Integer mappings:maxCardMapping)
		{
			int num=0;
			for(Pair<ArrayList<Integer>, ArrayList<Integer>> mipp:rMap.getMapMinimalConflictSets().get(mappings))
			{
				/*HashMap<Integer, Set<Integer>> commonEntailment=mipp.getCommonEntailment();
				for(Integer path:commonEntailment.keySet())
				{
					if(path==mappings)
						num=num+commonEntailment.get(path).size();
				}*/
				HashMap<Integer, Integer> commonEntailment=mipp.getCommonEntailment();
				for(Integer path:commonEntailment.keySet())
				{
					if(path==mappings)
						num=num+commonEntailment.get(path);
				}
			}
			map.put(mappings, num);
		}
		
		List<Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            //升序排序
            public int compare(Entry<Integer, Integer> o1,
                    Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }         
        });
		
		ArrayList<Integer> commonEntailmentMappping=new ArrayList<Integer>();
		int commonNum = -1;
		 for(Entry<Integer, Integer> mapping:list)
		{ 
            int key=mapping.getKey();
            if(commonNum==-1)
         	{
            	commonNum=mapping.getValue();
         		commonEntailmentMappping.add(key);
         	}
            else if(commonNum==mapping.getValue())
            	commonEntailmentMappping.add(key);
        	else 
        	{
				break;
			}     
        }
		return commonEntailmentMappping;
	}
	
	private ArrayList<Integer> getminimalWeight(ArrayList<Integer> commonEntailment) 
	{
		double minWeight=Integer.MAX_VALUE;
		HashMap<Double,ArrayList<Integer>> weightMapping=new HashMap<Double,ArrayList<Integer>>();
		for(Integer map:commonEntailment)
		{
			double weight=rMap.getMapping(map).getSimilarity();
			if(minWeight>weight)
			{
				minWeight=weight;
			}
			if(weightMapping.keySet().contains(weight))
			{
				weightMapping.get(weight).add(map);
			}
			else
			{
				ArrayList<Integer> list=new ArrayList<Integer>();
				list.add(map);
				weightMapping.put(weight, list);
			}	
		}
		ArrayList<Integer> WeightsMapping=new ArrayList<Integer>();
		WeightsMapping=weightMapping.get(minWeight);
		return WeightsMapping;		
	}
	
	private ArrayList<Integer> getminimalWeight2(ArrayList<Integer> commonEntailment) 
	{
		Map<Integer, Double> map = new TreeMap<Integer, Double>();
		for(Integer mappings:commonEntailment)
		{
			double weight=rMap.getMapping(mappings).getSimilarity();
			map.put(mappings, weight);
		}
		List<Entry<Integer, Double>> list = new ArrayList<Map.Entry<Integer,Double>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Double>>() {
            //升序排序
            public int compare(Entry<Integer, Double> o1,
                    Entry<Integer, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }         
        });
		
		ArrayList<Integer> WeightsMapping=new ArrayList<Integer>();
		double minWeight = -1;
		 for(Entry<Integer, Double> mapping:list)
		{ 
            int key=mapping.getKey();
            if(minWeight==-1)
         	{
            	minWeight=mapping.getValue();
            	WeightsMapping.add(key);
         	}
            else if(minWeight==mapping.getValue())
            	WeightsMapping.add(key);
        	else 
        	{
				break;
			}     
        }
		return WeightsMapping;		
	}
	
	private ArrayList<Integer> selectCandidateMappingByWeight(ArrayList<Integer> CardMapping,Boolean Judge) 
	{
		Map<Integer, Double> map = new TreeMap<Integer, Double>();
		for(Integer mappings:CardMapping)
		{
			double weight=rMap.getMapping(mappings).getSimilarity();
			map.put(mappings, weight);
		}

		List<Entry<Integer, Double>> list = new ArrayList<Map.Entry<Integer,Double>>(map.entrySet());
		
		
        //然后通过比较器来实现排序
		if(Judge==false)
		{
			Collections.sort(list,new Comparator<Map.Entry<Integer, Double>>() {// 升序排序
						public int compare(Entry<Integer, Double> o1,
								Entry<Integer, Double> o2) {
							return o1.getValue().compareTo(o2.getValue());
						}
					});
		}
		else {
			Collections.sort(list,new Comparator<Map.Entry<Integer, Double>>() {// 降序排序
				public int compare(Entry<Integer, Double> o1,
						Entry<Integer, Double> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
			});
		}
		
		ArrayList<Integer> WeightsMapping=new ArrayList<Integer>();
		
		double Weight = -1;
		for (Entry<Integer, Double> mapping : list) 
		{
			int key = mapping.getKey();
			if (Weight == -1) {
				Weight = mapping.getValue();
				WeightsMapping.add(key);
			} 
			else if (Weight == mapping.getValue())
				WeightsMapping.add(key);
			else {
			break;
			}
		}
		return WeightsMapping;		
	}
	
	
	/*private boolean ExistCommonEntailment(int mapping)
	{
		int num=0;
		boolean flag=true;
		for(Pair<ArrayList<Integer>, ArrayList<Integer>> mipp: rMap.getMapMinimalConflictSets().get(mapping))
		{
			HashMap<Path, Set<Integer>> commonEntailment=mipp.getCommonEntailment();
			boolean hasEntailments=false;
			for(Path path:commonEntailment.keySet())
			{
				if(path.contains(mapping))
				{
					hasEntailments=true;
					num=num+commonEntailment.get(path).size();
				}
			}	
			flag=flag&&hasEntailments;  //其实这个条件比较草率
			if(!flag)
				break;
		}	
		return flag;
	}*/
	
	private boolean ExistCommonEntailment2(int mapping)
	{
		int num=0;
		boolean flag=true;
		for(Pair<ArrayList<Integer>, ArrayList<Integer>> mipp: rMap.getMapMinimalConflictSets().get(mapping))
		{
			/*HashMap<Integer, Set<Integer>> commonEntailment=mipp.getCommonEntailment();
			boolean hasEntailments=false;
			if(commonEntailment.keySet().contains(mapping))  //其实这个条件比较草率
			{
				hasEntailments=true;
			}*/
			
			HashMap<Integer, Integer> commonEntailment=mipp.getCommonEntailment();
			boolean hasEntailments=false;
			if(commonEntailment.keySet().contains(mapping))  //其实这个条件比较草率
			{
				hasEntailments=true;
			}
			flag=flag&&hasEntailments; 
			if(!flag)
				break;
		}	
		return flag;
	}
	
	
	
	/*public HashMap<Path, Set<Integer>> getCommonEntailment(Pair<ArrayList<Integer>, ArrayList<Integer>> mipp)
	{
		HashMap<Path, Integer> conflictTailIndex1=new HashMap<Path, Integer>();
		HashMap<Path, Integer> conflictTailIndex2=new HashMap<Path, Integer>();
		for(int i=0;i<mipp.left.size()-1;i++)
		{
			int index=-1;
			int node1=mipp.left.get(i);
			int node2=mipp.left.get(i+1);		
			index=aml.getAlignment().getIndexBidirectional(node1, node2);
			if(index!=-1)
			{	
				conflictTailIndex1.put(new Path(index),node2);
			}
		}
		
		for(int i=0;i<mipp.right.size()-1;i++)
		{
			int index=-1;
			int node1=mipp.right.get(i);
			int node2=mipp.right.get(i+1);		
			index=aml.getAlignment().getIndexBidirectional(node1, node2);
			if(index!=-1)
			{	
				conflictTailIndex2.put(new Path(index),node2);
			}
		}
		
		HashMap<Path, Set<Integer>> commonEntailment=new HashMap<Path, Set<Integer>>();
		for(Path path1:conflictTailIndex1.keySet())
		{
			int tail1=conflictTailIndex1.get(path1);
			Set<Integer> ancestor1=aml.getRelationshipMap().getSuperClasses(tail1,false);
			for(Path path2:conflictTailIndex2.keySet())
			{
				int tail2=conflictTailIndex2.get(path2);
				Set<Integer> ancestor2=aml.getRelationshipMap().getSuperClasses(tail2,false);
				ancestor2.retainAll(ancestor1);
				if(!ancestor2.isEmpty())
				{
					commonEntailment.put(path1, ancestor2);
					commonEntailment.put(path2, ancestor2);
				}			
			}
		}
		return commonEntailment;
	}*/
	
	public Integer restoreRoleMapping(Integer index)
	{
		Mapping m=maps.get(index);
		String sourceString=m.getSourceURI();
		String targetString=m.getTargetURI();
		sourceString=sourceString.replace("exist_", "").replace("inverse_", "");
		targetString=targetString.replace("exist_", "").replace("inverse_", "");
		
		int sourceId=aml.getURIMap().getIndex(sourceString);
		int targetId=aml.getURIMap().getIndex(targetString);
		
		int originalIndex=aml.getAlignment().getIndexBidirectional(sourceId, targetId);		
		return originalIndex;	
	}
	
	public int obtainImpactPlus(Integer index)
	{
		Mapping m = aml.getAlignment().get(index);
		int source = m.getSourceId();
		int target = m.getTargetId();
		MappingRelation r=null;  //这个的关系是存在对应的情况,即当前mapping是什么关系，那么影响后的mapping也应有同样的关系
		int num=0;
		
		if(m.getRelationship().equals(MappingRelation.SUBCLASS)||m.getRelationship().equals(MappingRelation.SUPERCLASS))
		{
			r=m.getRelationship();
			Set<Integer> descendant1 = aml.getRelationshipMap().getSubClasses(source, false);
			Set<Integer> ancestor2 = aml.getRelationshipMap().getSuperClasses(target, false);
			descendant1.add(source);
			ancestor2.add(target);
			Set<Integer> set1= getRelatedMappingsNum(descendant1, ancestor2, r);

			Set<Integer> ancestor1 = aml.getRelationshipMap().getSuperClasses(source, false);
			Set<Integer> descendant2 = aml.getRelationshipMap().getSubClasses(target, false);
			ancestor1.add(source);
			descendant2.add(target);
			Set<Integer> set2 = getRelatedMappingsNum(ancestor1, descendant2, r);	
			set1.addAll(set2);
			num=set1.size();
		}
		else if(m.getRelationship().equals(MappingRelation.EQUIVALENCE))
		{
			r=MappingRelation.SUBCLASS;
			Set<Integer> descendant1 = aml.getRelationshipMap().getSubClasses(source, false);
			Set<Integer> ancestor2 = aml.getRelationshipMap().getSuperClasses(target, false);
			descendant1.add(source);
			ancestor2.add(target);			
			Set<Integer> set1 = getRelatedMappingsNum(descendant1, ancestor2, r);

			Set<Integer> ancestor1 = aml.getRelationshipMap().getSuperClasses(source, false);
			Set<Integer> descendant2 = aml.getRelationshipMap().getSubClasses(target, false);
			ancestor1.add(source);
			descendant2.add(target);
			Set<Integer> set2 = getRelatedMappingsNum(ancestor1, descendant2, r);	
			
			r=MappingRelation.SUPERCLASS;
			Set<Integer> set3 = getRelatedMappingsNum(descendant1, ancestor2, r);
			Set<Integer> set4 = getRelatedMappingsNum(ancestor1, descendant2, r);	
			set1.addAll(set2);
			set1.addAll(set3);
			set1.addAll(set4);
			num=set1.size();		
		}	
		return num;
	}

	public int obtainImpactConflict(Integer index) //这里实现的是简化的版本即统计MIPS冲突的情况，因为复杂的版本涉及到闭包，需要一直迭代
	{
		int num=0;
		HashSet<Set<Integer>> conflictSet=new HashSet<Set<Integer>>();
		if(rMap.MapMinimalConflictSet.keySet().contains(index))
		{
			HashSet<Pair<ArrayList<Integer>, ArrayList<Integer>>> MIPPSet = new HashSet(rMap.MapMinimalConflictSet.get(index));
			for (Pair<ArrayList<Integer>, ArrayList<Integer>> mipp : MIPPSet) 
			{
				// 通过路径来获取mappings(这里的mapping应该均是尚未标记的,因为无论执行approve或者reject操作，mapping相应的MIPS都会被消除)
				Set<Integer> mappings = rMap.getMappings(mipp.left, mipp.right);
				if (rMap.existRejectiveMapping(mappings, wantMappings, index)) // 这里可以结合wantmapping来消除冲突的情况
				{
					num++;
				}
			}
		}		
		return num;
	}
	
	public int obtainImpactConflict2(Integer index) //这里实现的是简化的版本即统计MIPS冲突的情况，因为复杂的版本涉及到闭包，需要一直迭代
	{
		//int num=0;
		HashSet<Set<Integer>> conflictSet=new HashSet<Set<Integer>>();
		if(rMap.MapMinimalConflictSet.keySet().contains(index))
		{
			HashSet<Pair<ArrayList<Integer>, ArrayList<Integer>>> MIPPSet = new HashSet(rMap.MapMinimalConflictSet.get(index));
			for (Pair<ArrayList<Integer>, ArrayList<Integer>> mipp : MIPPSet) 
			{
				// 通过路径来获取mappings(这里的mapping应该均是尚未标记的,因为无论执行approve或者reject操作，mapping相应的MIPS都会被消除)
				Set<Integer> mappings = rMap.getMappings(mipp.left, mipp.right);
				if (rMap.existRejectiveMapping(mappings, wantMappings, index)) // 这里可以结合wantmapping来消除冲突的情况
				{
					conflictSet.add(mappings);
					//num++;
				}
			}
		}		
		return conflictSet.size();
	}
	
	public int obtainImpactMinor(Integer index)
	{
		Mapping m = aml.getAlignment().get(index);
		int source = m.getSourceId();
		int target = m.getTargetId();
		MappingRelation r=MappingRelation.EQUIVALENCE;  //这个的关系是存在对应的情况,即当前mapping是什么关系，那么影响后的mapping也应有同样的关系
		int num=0;
				
		boolean flag=false;
		if(m.getRelationship().equals(MappingRelation.SUPERCLASS))
		{
			r=MappingRelation.SUBCLASS;
			flag=true;
		}
		else if(m.getRelationship().equals(MappingRelation.SUBCLASS))
		{
			r=MappingRelation.SUPERCLASS;
			flag=true;
		}
		
		if(flag)
		{
			Set<Integer> descendant1=aml.getRelationshipMap().getSubClasses(source,false);
			Set<Integer> ancestor2=aml.getRelationshipMap().getSuperClasses(target,false);		
			descendant1.add(source);
			ancestor2.add(target);	
			Set<Integer> set1=getRelatedMappingsNum(descendant1,ancestor2,r);
			
			Set<Integer> ancestor1=aml.getRelationshipMap().getSuperClasses(source,false);
			Set<Integer> descendant2=aml.getRelationshipMap().getSubClasses(target,false);	
			ancestor1.add(source);
			descendant2.add(target);	
			Set<Integer> set2=getRelatedMappingsNum(ancestor1,descendant2,r);
			set1.addAll(set2);
			num=set1.size();
		}
		else 
		{
			r=MappingRelation.SUBCLASS;
			Set<Integer> descendant1=aml.getRelationshipMap().getSubClasses(source,false);
			Set<Integer> ancestor2=aml.getRelationshipMap().getSuperClasses(target,false);	
			descendant1.add(source);
			ancestor2.add(target);
			Set<Integer> set1=getRelatedMappingsNum(descendant1,ancestor2,r);
			
			Set<Integer> ancestor1=aml.getRelationshipMap().getSuperClasses(source,false);
			Set<Integer> descendant2=aml.getRelationshipMap().getSubClasses(target,false);		
			ancestor1.add(source);
			descendant2.add(target);
			Set<Integer> set2=getRelatedMappingsNum(ancestor1,descendant2,r);
			
			r=MappingRelation.SUPERCLASS;
			Set<Integer> set3 = getRelatedMappingsNum(descendant1, ancestor2, r);
			Set<Integer> set4 = getRelatedMappingsNum(ancestor1, descendant2, r);	
			set1.addAll(set2);
			set1.addAll(set3);
			set1.addAll(set4);
			
			num=set1.size();			
		}			
		return num;
	}
	
	public Set<Integer> getRelatedMappingsNum(Set<Integer> set1, Set<Integer> set2, MappingRelation r)
	{
		Set<Integer> mappingSet=new HashSet<Integer>();
		for(Integer node1:set1)
		{
			for(Integer node2:set2)
			{
				int mapping=aml.getAlignment().getIndexBidirectional(node1, node2);	
				if(mapping!=-1&&maps.get(mapping).getStatus().equals(MappingStatus.UNKNOWN))
				{
					Mapping m = aml.getAlignment().get(mapping);
					if(m.getRelationship().equals(r)||m.getRelationship().equals(MappingRelation.EQUIVALENCE))
						mappingSet.add(mapping);
				}
			}
		}
		return mappingSet;
	}
	
	public void printDetailInformation(Mapping m)
	{
		int sourceId=m.getSourceId();
		int targetId=m.getTargetId();
		if(aml.getSource().getClasses().contains(sourceId))
		{
			Set<Integer> parent1=aml.getRelationshipMap().getParents(sourceId);
			Set<Integer> children1=aml.getRelationshipMap().getChildren(sourceId);
							
			System.out.println("The context of "+aml.getURIMap().getLocalName(sourceId)+" are listed as follow");
			System.out.print("The parents are: ");
			if(parent1.isEmpty())
				System.out.print("None");
			for(Integer par: parent1)
			{
				System.out.print(aml.getURIMap().getURI(par).replace(aml.getSource().getURI()+"#", "")+" ");				
			}
			System.out.println();
			System.out.print("The children are: ");
			if(children1.isEmpty())
				System.out.print("None");
			for(Integer child: children1)
			{
				System.out.print(aml.getURIMap().getURI(child).replace(aml.getSource().getURI()+"#", "")+" ");			
			}	
			System.out.println();		
			Set<Integer> parent2=aml.getRelationshipMap().getParents(targetId);
			Set<Integer> children2=aml.getRelationshipMap().getChildren(targetId);
			
			System.out.println("The context of "+aml.getURIMap().getLocalName(targetId)+" are listed as follow");
			System.out.print("The parents are: ");
			if(parent2.isEmpty())
				System.out.print("None");		
			for(Integer par: parent2)
			{
				System.out.print(aml.getURIMap().getURI(par).replace(aml.getTarget().getURI()+"#", "")+" ");
			}	
			System.out.println();
			System.out.print("The children are: ");
			if(children2.isEmpty())
				System.out.print("None");		
			for(Integer child: children2)
			{
				System.out.print(aml.getURIMap().getURI(child).replace(aml.getTarget().getURI()+"#", "")+" ");		
			}	
				
		}		
		else if(aml.getSource().getObjectProperties().contains(sourceId))
		{
			ObjectProperty objectProperty1=aml.getSource().getObjectProperty(sourceId);
			Set<Integer> domain1=objectProperty1.getDomain();
			Set<Integer> range1=objectProperty1.getRange();
			
			System.out.println("The context of "+aml.getURIMap().getLocalName(sourceId)+" are listed as follow");
			System.out.print("The domain are: ");
			if(domain1.isEmpty())
				System.out.print("None");
			for(Integer dom: domain1)
			{
				System.out.print(aml.getURIMap().getLocalName(dom)+" ");
			}
			System.out.println();
			System.out.print("The range are: ");
			if(range1.isEmpty())
				System.out.print("None");
			for(Integer ran: range1)
			{
				System.out.print(aml.getURIMap().getLocalName(ran)+" ");
			}	
			System.out.println();	
			
			if(aml.getTarget().getObjectProperties().contains(targetId))
			{
				ObjectProperty objectProperty2=aml.getTarget().getObjectProperty(targetId);
				Set<Integer> domain2=objectProperty2.getDomain();
				Set<Integer> range2=objectProperty2.getRange();
			
				System.out.println("The context of "+aml.getURIMap().getLocalName(targetId)+" are listed as follow");
				System.out.print("The domain are: ");
				if(domain2.isEmpty())
					System.out.print("None");
				for(Integer dom: domain2)
				{
					System.out.print(aml.getURIMap().getLocalName(dom)+" ");
				}
				System.out.println();
				System.out.print("The range are: ");
				if(range2.isEmpty())
					System.out.print("None");
				for(Integer ran: range2)
				{
					System.out.print(aml.getURIMap().getLocalName(ran)+" ");
				}	
			}
			else { //可能属性匹配的类型不一样，一个是对象属性，一个数值属性
				DataProperty dataProperty2=aml.getTarget().getDataProperty(targetId);
				Set<Integer> domain2=dataProperty2.getDomain();
				Set<String> range2=dataProperty2.getRange();
				
				System.out.println("The context of "+aml.getURIMap().getLocalName(targetId)+" are listed as follow");
				System.out.print("The domains are: ");
				if(domain2.isEmpty())
					System.out.print("None");
				for(Integer dom: domain2)
				{
					System.out.print(aml.getURIMap().getLocalName(dom)+" ");
				}
				System.out.println();
				System.out.print("The ranges are: ");
				if(range2.isEmpty())
					System.out.print("None");
				for(String ran: range2)
				{
					System.out.print(ran+" ");
				}	
			}
			
		}
		else if(aml.getSource().getDataProperties().contains(sourceId))
		{
			DataProperty dataProperty1=aml.getSource().getDataProperty(sourceId);
			Set<Integer> domain1=dataProperty1.getDomain();
			Set<String> range1=dataProperty1.getRange();
			
			System.out.println("The context of "+aml.getURIMap().getLocalName(sourceId)+" are listed as follow");
			System.out.print("The domains are: ");
			if(domain1.isEmpty())
				System.out.print("None");
			for(Integer dom: domain1)
			{
				System.out.print(aml.getURIMap().getLocalName(dom)+" ");
			}
			System.out.println();
			System.out.print("The ranges are: ");
			if(range1.isEmpty())
				System.out.print("None");
			for(String ran: range1)
			{
				System.out.print(ran+" ");
			}	
			System.out.println();	
			
			if(aml.getTarget().getDataProperties().contains(targetId))
			{
				DataProperty dataProperty2=aml.getTarget().getDataProperty(targetId);
				Set<Integer> domain2=dataProperty2.getDomain();
				Set<String> range2=dataProperty2.getRange();
			
				System.out.println("The context of "+aml.getURIMap().getLocalName(targetId)+" are listed as follow");
				System.out.print("The domains are: ");
				if(domain2.isEmpty())
					System.out.print("None");
				for(Integer dom: domain2)
				{
					System.out.print(aml.getURIMap().getLocalName(dom)+" ");
				}
				System.out.println();
				System.out.print("The ranges are: ");
				if(range2.isEmpty())
					System.out.print("None");
				for(String ran: range2)
				{
					System.out.print(ran+" ");
				}	
			}
			else { //可能属性匹配的类型不一样，一个是对象属性，一个数值属性
				ObjectProperty objectProperty2=aml.getTarget().getObjectProperty(targetId);
				Set<Integer> domain2=objectProperty2.getDomain();
				Set<Integer> range2=objectProperty2.getRange();
			
				System.out.println("The context of "+aml.getURIMap().getLocalName(targetId)+" are listed as follow");
				System.out.print("The domain are: ");
				if(domain2.isEmpty())
					System.out.print("None");
				for(Integer dom: domain2)
				{
					System.out.print(aml.getURIMap().getLocalName(dom)+" ");
				}
				System.out.println();
				System.out.print("The range are: ");
				if(range2.isEmpty())
					System.out.print("None");
				for(Integer ran: range2)
				{
					System.out.print(aml.getURIMap().getLocalName(ran)+" ");
				}	
			}
		}
		System.out.println();
		int index=aml.getAlignment().getIndexBidirectional(sourceId, targetId);
		if(rMap.getMapMinimalConflictSets().containsKey(index))
			System.out.println("This mapping is an incoherent mapping.");
	}
		
}




