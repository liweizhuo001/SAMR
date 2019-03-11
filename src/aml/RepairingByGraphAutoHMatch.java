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
* Test-runs AgreementMakerLight in Eclipse.                                   *
*                                                                             *
* @author Daniel Faria                                                        *
******************************************************************************/
package aml;

public class RepairingByGraphAutoHMatch
{

//Main Method
	
	public static void main(String[] args) throws Exception
	{
		
		long time = System.currentTimeMillis()/1000;
		
		//Conference		
		//SimpleTest	
		/*String sourcePath  = "exampleOntology/example1.owl";
	    String targetPath  = "exampleOntology/example2.owl";
	    String alignPath = "exampleOntology/12.rdf";
	    String referencePath   = "testdata/conference/references/cmt-conference.rdf";
		String outputPath="exampleOntology/123.rdf";*/
		
		/*String sourcePath  = "exampleOntology/example3.owl";
	    String targetPath  = "exampleOntology/example2.owl";
	    String alignPath = "exampleOntology/32.rdf";
	    String referencePath   = "testdata/conference/references/cmt-conference.rdf";
		String outputPath="exampleOntology/123.rdf";*/
		
		/*String sourcePath  = "exampleOntology/cmt_test4.owl";
		String targetPath  = "exampleOntology/confOf_test4.owl";
		String alignPath = "exampleOntology/cmt-confOf_test.rdf";
		String referencePath   = "testdata/conference/references/cmt-conference.rdf";
		String outputPath="exampleOntology/1234.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/edas.owl";
		String alignPath = "exampleOntology/GMap-cmt-edas_test.rdf";
		String referencePath   = "testdata/conference/references/cmt-conference.rdf";
		String outputPath="exampleOntology/1234.rdf";*/
		
		//Experiment	
		//HMatch
//		String sourcePath  = "testdata/conference/ontologies/cmt.owl";
//		String targetPath  = "testdata/conference/ontologies/Conference.owl";
//		String alignPath = "alignment4Experiment/HMatch-cmt-conference.rdf";
//		String referencePath   = "testdata/conference/references/cmt-conference.rdf";
//		//String outputPath="Results/One2One/HMatch-cmt-conference.rdf";
//		//String outputPath="Results/ChristianTool_new/HMatch-cmt-conference.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/HMatch-cmt-conference.rdf";
//		String outputPath="Results/OptimalImpactor_Complete/HMatch-cmt-conference.rdf";
		
			
//		String sourcePath  = "testdata/conference/ontologies/cmt.owl";
//		String targetPath  = "testdata/conference/ontologies/confOf.owl";
//		String alignPath = "alignment4Experiment/HMatch-cmt-confof.rdf";
//		String referencePath   = "testdata/conference/references/cmt-confof.rdf";
//		//String outputPath="Results/One2One/HMatch-cmt-confof.rdf";
//		//String outputPath="Results/ChristianTool_new/HMatch-cmt-confof.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/HMatch-cmt-confof.rdf";
//		String outputPath="Results/OptimalImpactor_Complete/HMatch-cmt-confof1.rdf";
		
		
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/edas.owl";
		String alignPath = "alignment4Experiment/HMatch-cmt-edas.rdf";
		String referencePath   = "testdata/conference/references/cmt-edas.rdf";
		//String outputPath="Results/One2One/HMatch-cmt-edas.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-cmt-edas.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-cmt-edas.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-cmt-edas.rdf";*/
		
		
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/ekaw.owl";
		String alignPath = "alignment4Experiment/HMatch-cmt-ekaw.rdf";
		String referencePath   = "testdata/conference/references/cmt-ekaw.rdf";
		//String outputPath="Results/One2One/HMatch-cmt-ekaw.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-cmt-ekaw.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-cmt-ekaw.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-cmt-ekaw.rdf";*/
		
		
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/HMatch-cmt-iasted.rdf";
		String referencePath   = "testdata/conference/references/cmt-iasted.rdf";
		//String outputPath="Results/One2One/HMatch-cmt-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-cmt-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-cmt-iasted.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-cmt-iasted.rdf";*/
		
		
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/HMatch-cmt-sigkdd.rdf";
		String referencePath   = "testdata/conference/references/cmt-sigkdd.rdf";
		//String outputPath="Results/One2One/HMatch-cmt-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-cmt-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-cmt-sigkdd.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-cmt-sigkdd.rdf";*/
				
		/*String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/confOf.owl";
		String alignPath = "alignment4Experiment/HMatch-conference-confof.rdf";
		String referencePath= "testdata/conference/references/conference-confOf.rdf";
		//String outputPath="Results/One2One/HMatch-conference-confof.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-conference-confof.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-conference-confof.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-conference-confof.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/edas.owl";
		String alignPath = "alignment4Experiment/HMatch-conference-edas.rdf";
		String referencePath= "testdata/conference/references/conference-edas.rdf";
		//String outputPath="Results/One2One/HMatch-conference-edas.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-conference-edas.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-conference-edas.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-conference-edas.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/ekaw.owl";
		String alignPath = "alignment4Experiment/HMatch-conference-ekaw.rdf";
		String referencePath= "testdata/conference/references/conference-ekaw.rdf";
		//String outputPath="Results/One2One/HMatch-conference-ekaw.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-conference-ekaw.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-conference-ekaw.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-conference-ekaw.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/HMatch-conference-iasted.rdf";
		String referencePath= "testdata/conference/references/conference-iasted.rdf";
		//String outputPath="Results/One2One/HMatch-conference-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-conference-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-conference-iasted.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-conference-iasted.rdf";*/
		
		String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/HMatch-conference-sigkdd.rdf";
		String referencePath= "testdata/conference/references/conference-sigkdd.rdf";
		//String outputPath="Results/One2One/HMatch-conference-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-conference-sigkdd.rdf";
		String outputPath="Results/StructureImpactor_Complete/HMatch-conference-sigkdd.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/HMatch-conference-sigkdd.rdf";
		
		/*String sourcePath  = "testdata/conference/ontologies/confOf.owl";
		String targetPath  = "testdata/conference/ontologies/edas.owl";
		String alignPath = "alignment4Experiment/HMatch-confof-edas.rdf";
		String referencePath= "testdata/conference/references/confOf-edas.rdf";
		//String outputPath="Results/One2One/HMatch-confof-edas.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-confof-edas.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-confof-edas.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-confof-edas.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/confOf.owl";
		String targetPath  = "testdata/conference/ontologies/ekaw.owl";
		String alignPath = "alignment4Experiment/HMatch-confof-ekaw.rdf";
		String referencePath= "testdata/conference/references/confOf-ekaw.rdf";
		//String outputPath="Results/One2One/HMatch-confof-ekaw.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-confof-ekaw.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-confof-ekaw.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-confof-ekaw.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/confOf.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/HMatch-confof-iasted.rdf";
		String referencePath= "testdata/conference/references/confOf-iasted.rdf";
		//String outputPath="Results/One2One/HMatch-confof-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-confof-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-confof-iasted.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-confof-iasted.rdf";*/
		
		
		/*String sourcePath  = "testdata/conference/ontologies/confOf.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/HMatch-confof-sigkdd.rdf";
		String referencePath= "testdata/conference/references/confOf-sigkdd.rdf";
		//String outputPath="Results/One2One/HMatch-confof-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-confof-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-confof-sigkdd.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-confof-sigkdd.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/edas.owl";
		String targetPath  = "testdata/conference/ontologies/ekaw.owl";
		String alignPath = "alignment4Experiment/HMatch-edas-ekaw.rdf";
		String referencePath= "testdata/conference/references/edas-ekaw.rdf";
		//String outputPath="Results/One2One/HMatch-edas-ekaw.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-edas-ekaw.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-edas-ekaw.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-edas-ekaw.rdf";*/
		
		
		/*String sourcePath  = "testdata/conference/ontologies/edas.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/HMatch-edas-iasted.rdf";
		String referencePath= "testdata/conference/references/edas-iasted.rdf";
		//String outputPath="Results/One2One/HMatch-edas-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-edas-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-edas-iasted.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-edas-iasted.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/edas.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/HMatch-edas-sigkdd.rdf";
		String referencePath= "testdata/conference/references/edas-sigkdd.rdf";
		//String outputPath="Results/One2One/HMatch-edas-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-edas-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-edas-sigkdd.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-edas-sigkdd.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/ekaw.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/HMatch-ekaw-iasted.rdf";
		String referencePath= "testdata/conference/references/ekaw-iasted.rdf";
		//String outputPath="Results/One2One/HMatch-ekaw-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-ekaw-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-ekaw-iasted.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-ekaw-iasted.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/ekaw.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/HMatch-ekaw-sigkdd.rdf";
		String referencePath= "testdata/conference/references/ekaw-sigkdd.rdf";
		//String outputPath="Results/One2One/HMatch-ekaw-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-ekaw-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-ekaw-sigkdd.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-ekaw-sigkdd.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/iasted.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/HMatch-iasted-sigkdd.rdf";
		String referencePath= "testdata/conference/references/iasted-sigkdd.rdf";
		//String outputPath="Results/One2One/HMatch-iasted-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-iasted-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-iasted-sigkdd.rdf";
		String outputPath="Results/OptimalImpactor_Complete/HMatch-iasted-sigkdd.rdf";*/
		
//		String sourcePath  = "testdata/anatomy/mouse.owl";
//		String targetPath  = "testdata/anatomy/human.owl";
//		String alignPath = "alignment4Experiment/HMatch-mouse-human.rdf";
//		String referencePath   = "testdata/anatomy/reference_2015.rdf";
//		String outputPath="Results/One2One/HMatch-mouse-human.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-mouse-human.rdf";
		//String outputPath="Results/StructureImpactor_Complete/HMatch-mouse-human.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/HMatch-mouse-human.rdf";
		
		/*String sourcePath  = "testdata/largeBio/oaei_FMA_small_overlapping_nci.owl";
		String targetPath  = "testdata/largeBio/oaei_NCI_small_overlapping_fma.owl";
		String alignPath = "alignment4Experiment/HMatch-fma-nci-small.rdf";
		String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_mappings_with_flagged_repairs.rdf";
		//String outputPath="Results/One2One/HMatch-fma-nci-small.rdf";
		//String outputPath="Results/ChristianTool_new/HMatch-fma-nci-small.rdf";
		String outputPath="Results/StructureImpactor_Complete/HMatch-fma-nci-small.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/HMatch-fma-nci-small.rdf";
*/		
				
		AML aml = AML.getInstance();
		aml.openOntologies(sourcePath, targetPath);	
		//aml.matchAuto();  //�Զ���ƥ��
		//aml.getAlignment();
		
		if(!referencePath.equals(""))
		{
			aml.openReferenceAlignment(referencePath);
			//aml.evaluate();
			//aml.evaluate2();
			//System.out.println(aml.getEvaluation());			
		}
		if(!alignPath.equals(""))
		{
			aml.openAlignment(alignPath);
			//aml.repair();  //����logical incoherent ���޸�
			aml.repairByGraph();  //����logical incoherent ���޸�
		}
		aml.simpleEvaluate();
		//��֤path�ĳ���
		//aml.getPath();		
	
		
		System.out.println("The whole repair by our method is " + (System.currentTimeMillis()/1000-time) + " seconds");
		if(!outputPath.equals(""))
			aml.saveAlignmentRDF(outputPath);
			
	}
}