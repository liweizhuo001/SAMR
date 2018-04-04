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

public class RepairingByGraphASMOV
{

//Main Method
	
	public static void main(String[] args) throws Exception
	{
		
		long time = System.currentTimeMillis()/1000;		
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
		//asmov
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/Conference.owl";
		String alignPath = "alignment4Experiment/asmov-cmt-conference.rdf";
		String referencePath   = "testdata/conference/references/cmt-conference.rdf";
		//String outputPath="Results/One2One/asmov-cmt-conference.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-cmt-conference.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-cmt-conference.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-cmt-conference2.rdf";*/
		
			
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/confOf.owl";
		String alignPath = "alignment4Experiment/asmov-cmt-confof.rdf";
		String referencePath   = "testdata/conference/references/cmt-confof.rdf";
		//String outputPath="Results/One2One/asmov-cmt-confof.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-cmt-confof.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-cmt-confof.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-cmt-confof2.rdf";*/
		
		
		String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/edas.owl";
		String alignPath = "alignment4Experiment/asmov-cmt-edas.rdf";
		String referencePath   = "testdata/conference/references/cmt-edas.rdf";
		//String outputPath="Results/One2One/asmov-cmt-edas.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-cmt-edas.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-cmt-edas.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-cmt-edas2.rdf";
		
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/ekaw.owl";
		String alignPath = "alignment4Experiment/asmov-cmt-ekaw.rdf";
		String referencePath   = "testdata/conference/references/cmt-ekaw.rdf";
		//String outputPath="Results/One2One/asmov-cmt-ekaw.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-cmt-ekaw.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-cmt-ekaw.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-cmt-ekaw2.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/asmov-cmt-iasted.rdf";
		String referencePath   = "testdata/conference/references/cmt-iasted.rdf";
		//String outputPath="Results/One2One/asmov-cmt-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-cmt-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-cmt-iasted.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-cmt-iasted2.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/asmov-cmt-sigkdd.rdf";
		String referencePath   = "testdata/conference/references/cmt-sigkdd.rdf";
		//String outputPath="Results/One2One/asmov-cmt-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-cmt-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-cmt-sigkdd.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-cmt-sigkdd2.rdf";*/
				
		/*String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/confOf.owl";
		String alignPath = "alignment4Experiment/asmov-conference-confof.rdf";
		String referencePath= "testdata/conference/references/conference-confOf.rdf";
		//String outputPath="Results/One2One/asmov-conference-confof.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-conference-confof.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-conference-confof.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-conference-confof2.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/edas.owl";
		String alignPath = "alignment4Experiment/asmov-conference-edas.rdf";
		String referencePath= "testdata/conference/references/conference-edas.rdf";
		//String outputPath="Results/One2One/asmov-conference-edas.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-conference-edas.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-conference-edas.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-conference-edas2.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/ekaw.owl";
		String alignPath = "alignment4Experiment/asmov-conference-ekaw.rdf";
		String referencePath= "testdata/conference/references/conference-ekaw.rdf";
		//String outputPath="Results/One2One/asmov-conference-ekaw.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-conference-ekaw.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-conference-ekaw.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-conference-ekaw2.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/asmov-conference-iasted.rdf";
		String referencePath= "testdata/conference/references/conference-iasted.rdf";
		//String outputPath="Results/One2One/asmov-conference-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-conference-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-conference-iasted.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-conference-iasted2.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/asmov-conference-sigkdd.rdf";
		String referencePath= "testdata/conference/references/conference-sigkdd.rdf";
		//String outputPath="Results/One2One/asmov-conference-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-conference-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-conference-sigkdd.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-conference-sigkdd2.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/confOf.owl";
		String targetPath  = "testdata/conference/ontologies/edas.owl";
		String alignPath = "alignment4Experiment/asmov-confof-edas.rdf";
		String referencePath= "testdata/conference/references/confOf-edas.rdf";
		//String outputPath="Results/One2One/asmov-confof-edas.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-confof-edas.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-confof-edas.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-confof-edas2.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/confOf.owl";
		String targetPath  = "testdata/conference/ontologies/ekaw.owl";
		String alignPath = "alignment4Experiment/asmov-confof-ekaw.rdf";
		String referencePath= "testdata/conference/references/confOf-ekaw.rdf";
		//String outputPath="Results/One2One/asmov-confof-ekaw.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-confof-ekaw.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-confof-ekaw.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-confof-ekaw2.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/confOf.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/asmov-confof-iasted.rdf";
		String referencePath= "testdata/conference/references/confOf-iasted.rdf";
		String outputPath="Results/One2One/asmov-confof-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-confof-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-confof-iasted.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-confof-iasted2.rdf";
*/		
		
		/*String sourcePath  = "testdata/conference/ontologies/confOf.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/asmov-confof-sigkdd.rdf";
		String referencePath= "testdata/conference/references/confOf-sigkdd.rdf";
		//String outputPath="Results/One2One/asmov-confof-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-confof-sigkdd.rdf";
		String outputPath="Results/StructureImpactor_Complete/asmov-confof-sigkdd.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-confof-sigkdd2.rdf";
*/		
		
		/*String sourcePath  = "testdata/conference/ontologies/edas.owl";
		String targetPath  = "testdata/conference/ontologies/ekaw.owl";
		String alignPath = "alignment4Experiment/asmov-edas-ekaw.rdf";
		String referencePath= "testdata/conference/references/edas-ekaw.rdf";
		//String outputPath="Results/One2One/asmov-edas-ekaw.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-edas-ekaw.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-edas-ekaw.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-edas-ekaw2.rdf";*/
		
		
		/*String sourcePath  = "testdata/conference/ontologies/edas.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/asmov-edas-iasted.rdf";
		String referencePath= "testdata/conference/references/edas-iasted.rdf";
		//String outputPath="Results/One2One/asmov-edas-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-edas-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-edas-iasted.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-edas-iasted2.rdf";*/
		
		
		/*String sourcePath  = "testdata/conference/ontologies/edas.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/asmov-edas-sigkdd.rdf";
		String referencePath= "testdata/conference/references/edas-sigkdd.rdf";
		//String outputPath="Results/One2One/asmov-edas-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-edas-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-edas-sigkdd.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-edas-sigkdd2.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/ekaw.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/asmov-ekaw-iasted.rdf";
		String referencePath= "testdata/conference/references/ekaw-iasted.rdf";
		//String outputPath="Results/One2One/asmov-ekaw-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-ekaw-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-ekaw-iasted.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-ekaw-iasted2.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/ekaw.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/asmov-ekaw-sigkdd.rdf";
		String referencePath= "testdata/conference/references/ekaw-sigkdd.rdf";
		//String outputPath="Results/One2One/asmov-ekaw-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-ekaw-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-ekaw-sigkdd.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-ekaw-sigkdd2.rdf";*/
		
		/*String sourcePath  = "testdata/conference/ontologies/iasted.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/asmov-iasted-sigkdd.rdf";
		String referencePath= "testdata/conference/references/iasted-sigkdd.rdf";
		//String outputPath="Results/One2One/asmov-iasted-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-iasted-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-iasted-sigkdd.rdf";
		String outputPath="Results/OptimalImpactor_Complete/asmov-iasted-sigkdd2.rdf";*/
				
		AML aml = AML.getInstance();
		aml.openOntologies(sourcePath, targetPath);
		if(!alignPath.equals(""))
		{
			aml.openAlignment(alignPath);
			aml.repairByGraph();  //基于logical incoherent 的修复
		}
		//验证path的长度
		//aml.getPath();		
		if(!referencePath.equals(""))
		{
			aml.openReferenceAlignment(referencePath);
			aml.simpleEvaluate();		
		}
		
		System.out.println("The whole repair by our method is " + (System.currentTimeMillis()/1000-time) + " seconds");
		if(!outputPath.equals(""))
			aml.saveAlignmentRDF(outputPath);
			
	}
}