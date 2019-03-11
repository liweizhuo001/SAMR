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

public class RepairingByGraphAutoASMOV
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
		//asmov
//		String sourcePath  = "testdata/conference/ontologies/cmt.owl";
//		String targetPath  = "testdata/conference/ontologies/Conference.owl";
//		String alignPath = "alignment4Experiment/asmov-cmt-conference.rdf";
//		String referencePath   = "testdata/conference/references/cmt-conference.rdf";
//		String outputPath="Results/One2One/asmov-cmt-conference.rdf";
//		//String outputPath="Results/ChristianTool_new/asmov-cmt-conference.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/asmov-cmt-conference.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/asmov-cmt-conference.rdf";
		
			
//		String sourcePath  = "testdata/conference/ontologies/cmt.owl";
//		String targetPath  = "testdata/conference/ontologies/confOf.owl";
//		String alignPath = "alignment4Experiment/asmov-cmt-confof.rdf";
//		String referencePath   = "testdata/conference/references/cmt-confof.rdf";
//		String outputPath="Results/One2One/asmov-cmt-confof.rdf";
//		//String outputPath="Results/ChristianTool_new/asmov-cmt-confof.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/asmov-cmt-confof.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/asmov-cmt-confof.rdf";
		
		
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/edas.owl";
		String alignPath = "alignment4Experiment/asmov-cmt-edas.rdf";
		String referencePath   = "testdata/conference/references/cmt-edas.rdf";
		String outputPath="Results/One2One/asmov-cmt-edas.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-cmt-edas.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-cmt-edas.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-cmt-edas.rdf";
*/		
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/ekaw.owl";
		String alignPath = "alignment4Experiment/asmov-cmt-ekaw.rdf";
		String referencePath   = "testdata/conference/references/cmt-ekaw.rdf";
		String outputPath="Results/One2One/asmov-cmt-ekaw.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-cmt-ekaw.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-cmt-ekaw.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-cmt-ekaw.rdf";
*/		
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/asmov-cmt-iasted.rdf";
		String referencePath   = "testdata/conference/references/cmt-iasted.rdf";
		String outputPath="Results/One2One/asmov-cmt-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-cmt-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-cmt-iasted.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-cmt-iasted.rdf";
*/		
		/*String sourcePath  = "testdata/conference/ontologies/cmt.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/asmov-cmt-sigkdd.rdf";
		String referencePath   = "testdata/conference/references/cmt-sigkdd.rdf";
		String outputPath="Results/One2One/asmov-cmt-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-cmt-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-cmt-sigkdd.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-cmt-sigkdd.rdf";
*/				
		/*String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/confOf.owl";
		String alignPath = "alignment4Experiment/asmov-conference-confof.rdf";
		String referencePath= "testdata/conference/references/conference-confOf.rdf";
		String outputPath="Results/One2One/asmov-conference-confof.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-conference-confof.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-conference-confof.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-conference-confof.rdf";
*/		
		String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/edas.owl";
		String alignPath = "alignment4Experiment/asmov-conference-edas.rdf";
		String referencePath= "testdata/conference/references/conference-edas.rdf";
		//String outputPath="Results/One2One/asmov-conference-edas.rdf";
		String outputPath="Results/ChristianTool_new/asmov-conference-edas.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-conference-edas.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-conference-edas.rdf";
		
		/*String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/ekaw.owl";
		String alignPath = "alignment4Experiment/asmov-conference-ekaw.rdf";
		String referencePath= "testdata/conference/references/conference-ekaw.rdf";
		String outputPath="Results/One2One/asmov-conference-ekaw.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-conference-ekaw.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-conference-ekaw.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-conference-ekaw.rdf";
*/		
		/*String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/asmov-conference-iasted.rdf";
		String referencePath= "testdata/conference/references/conference-iasted.rdf";
		String outputPath="Results/One2One/asmov-conference-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-conference-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-conference-iasted.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-conference-iasted.rdf";
*/		
		/*String sourcePath  = "testdata/conference/ontologies/Conference.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/asmov-conference-sigkdd.rdf";
		String referencePath= "testdata/conference/references/conference-sigkdd.rdf";
		String outputPath="Results/One2One/asmov-conference-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-conference-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-conference-sigkdd.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-conference-sigkdd.rdf";
*/		
		/*String sourcePath  = "testdata/conference/ontologies/confOf.owl";
		String targetPath  = "testdata/conference/ontologies/edas.owl";
		String alignPath = "alignment4Experiment/asmov-confof-edas.rdf";
		String referencePath= "testdata/conference/references/confOf-edas.rdf";
		String outputPath="Results/One2One/asmov-confof-edas.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-confof-edas.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-confof-edas.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-confof-edas.rdf";
*/		
		/*String sourcePath  = "testdata/conference/ontologies/confOf.owl";
		String targetPath  = "testdata/conference/ontologies/ekaw.owl";
		String alignPath = "alignment4Experiment/asmov-confof-ekaw.rdf";
		String referencePath= "testdata/conference/references/confOf-ekaw.rdf";
		String outputPath="Results/One2One/asmov-confof-ekaw.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-confof-ekaw.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-confof-ekaw.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-confof-ekaw.rdf";
*/		
		/*String sourcePath  = "testdata/conference/ontologies/confOf.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/asmov-confof-iasted.rdf";
		String referencePath= "testdata/conference/references/confOf-iasted.rdf";
		String outputPath="Results/One2One/asmov-confof-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-confof-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-confof-iasted.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-confof-iasted.rdf";
*/		
		
		/*String sourcePath  = "testdata/conference/ontologies/confOf.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/asmov-confof-sigkdd.rdf";
		String referencePath= "testdata/conference/references/confOf-sigkdd.rdf";
		String outputPath="Results/One2One/asmov-confof-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-confof-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-confof-sigkdd.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-confof-sigkdd.rdf";
*/		
		
		/*String sourcePath  = "testdata/conference/ontologies/edas.owl";
		String targetPath  = "testdata/conference/ontologies/ekaw.owl";
		String alignPath = "alignment4Experiment/asmov-edas-ekaw.rdf";
		String referencePath= "testdata/conference/references/edas-ekaw.rdf";
		String outputPath="Results/One2One/asmov-edas-ekaw.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-edas-ekaw.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-edas-ekaw.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-edas-ekaw.rdf";
*/		
		
//		String sourcePath  = "testdata/conference/ontologies/edas.owl";
//		String targetPath  = "testdata/conference/ontologies/iasted.owl";
//		String alignPath = "alignment4Experiment/asmov-edas-iasted.rdf";
//		String referencePath= "testdata/conference/references/edas-iasted.rdf";
//		//String outputPath="Results/One2One/asmov-edas-iasted.rdf";
//		String outputPath="Results/ChristianTool_new/asmov-edas-iasted.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/asmov-edas-iasted.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/asmov-edas-iasted.rdf";
		
		
		/*String sourcePath  = "testdata/conference/ontologies/edas.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/asmov-edas-sigkdd.rdf";
		String referencePath= "testdata/conference/references/edas-sigkdd.rdf";
		String outputPath="Results/One2One/asmov-edas-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-edas-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-edas-sigkdd.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-edas-sigkdd.rdf";
*/		
		/*String sourcePath  = "testdata/conference/ontologies/ekaw.owl";
		String targetPath  = "testdata/conference/ontologies/iasted.owl";
		String alignPath = "alignment4Experiment/asmov-ekaw-iasted.rdf";
		String referencePath= "testdata/conference/references/ekaw-iasted.rdf";
		String outputPath="Results/One2One/asmov-ekaw-iasted.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-ekaw-iasted.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-ekaw-iasted.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-ekaw-iasted.rdf";
*/		
		/*String sourcePath  = "testdata/conference/ontologies/ekaw.owl";
		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
		String alignPath = "alignment4Experiment/asmov-ekaw-sigkdd.rdf";
		String referencePath= "testdata/conference/references/ekaw-sigkdd.rdf";
		String outputPath="Results/One2One/asmov-ekaw-sigkdd.rdf";
		//String outputPath="Results/ChristianTool_new/asmov-ekaw-sigkdd.rdf";
		//String outputPath="Results/StructureImpactor_Complete/asmov-ekaw-sigkdd.rdf";
		//String outputPath="Results/OptimalImpactor_Complete/asmov-ekaw-sigkdd.rdf";
*/		
//		String sourcePath  = "testdata/conference/ontologies/iasted.owl";
//		String targetPath  = "testdata/conference/ontologies/sigkdd.owl";
//		String alignPath = "alignment4Experiment/asmov-iasted-sigkdd.rdf";
//		String referencePath= "testdata/conference/references/iasted-sigkdd.rdf";
//		String outputPath="Results/One2One/asmov-iasted-sigkdd.rdf";
//		//String outputPath="Results/ChristianTool_new/asmov-iasted-sigkdd.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/asmov-iasted-sigkdd.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/asmov-iasted-sigkdd.rdf";
		
		
//		String sourcePath  = "testdata/anatomy/mouse.owl";
//		String targetPath  = "testdata/anatomy/human.owl";
//		String alignPath = "alignment4Experiment/asmov-mouse-human.rdf";
//		String referencePath   = "testdata/anatomy/reference_2015.rdf";
//		//String outputPath="Results/One2One/Interactive-asmov-mouse-human.rdf";
//		//String outputPath="Results/ChristianTool_new/Interactive-asmov-mouse-human.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/Interactive-asmov-mouse-human.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/Interactive-asmov-mouse-human.rdf";
//		//String outputPath="Results/Interactive-asmov-mouse-human-repaired.rdf";
//		String outputPath="Results/Interactive-5-2-reliable-asmov-mouse-human-repaired.rdf";
		
//		String sourcePath  = "testdata/anatomy/mouse.owl";
//		String targetPath  = "testdata/anatomy/human.owl";
//		String alignPath = "alignment4Experiment/FCA_Map-mouse-human.rdf";
//		String referencePath   = "testdata/anatomy/reference_2015.rdf";
//		//String outputPath="Results/One2One/Interactive-FCA_Map-mouse-human.rdf";
//		//String outputPath="Results/ChristianTool_new/Interactive-FCA_Map-mouse-human.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/Interactive-FCA_Map-mouse-human.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/Interactive-FCA_Map-mouse-human.rdf";
//		String outputPath="Results/Interactive-5-reliable-FCA_Map-mouse-human-repaired.rdf";

		
//		String sourcePath  = "testdata/anatomy/mouse.owl";
//		String targetPath  = "testdata/anatomy/human.owl";
//		String alignPath = "alignment4Experiment/LogMapLite-mouse-human-C.rdf";
//		String referencePath   = "testdata/anatomy/reference_2015.rdf";
//		//String outputPath="Results/One2One/Interactive-LogMapLite-mouse-human.rdf";
//		//String outputPath="Results/ChristianTool_new/Interactive-LogMapLite-mouse-human.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/Interactive-LogMapLite-mouse-human.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/Interactive-LogMapLite-mouse-human.rdf";
//		String outputPath="Results/Interactive-5-2-reliable-LogMapLite-mouse-human-repaired.rdf";
		
//		String sourcePath  = "testdata/anatomy/mouse.owl";
//		String targetPath  = "testdata/anatomy/human.owl";
//		String alignPath = "alignment4Experiment/AML_M-mouse-human.rdf";
//		String referencePath   = "testdata/anatomy/reference_2015.rdf";
//		//String outputPath="Results/One2One/Interactive-AML_M-mouse-human.rdf";
//		//String outputPath="Results/ChristianTool_new/Interactive-AML_M-mouse-human.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/Interactive-AML_M-mouse-human.rdf";
//		//String outputPath="Results/Interactive-OptimalImpactor_Complete/AML_M-mouse-human.rdf";
//		String outputPath="Results/Interactive-5-reliable-AML_M-mouse-human.rdf";
	
		
//		String sourcePath  = "testdata/largeBio/oaei_FMA_small_overlapping_nci.owl";
//		String targetPath  = "testdata/largeBio/oaei_NCI_small_overlapping_fma.owl";
//		String alignPath = "alignment4Experiment/asmov-fma-nci-small.rdf";
//		//String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_mappings_with_flagged_repairs.rdf";
//		//String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_mappings_standard.rdf";
//		String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_reference.rdf";
//		//String outputPath="Results/One2One/Interactive-asmov-fma-nci-small.rdf";
//		//String outputPath="Results/ChristianTool_new/Interactive-asmov-fma-nci-small.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/Simple-Interactive-asmov-fma-nci-small.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/asmov-fma-nci-small.rdf";
//		//String outputPath="Results/Interactive-asmov-fma-nci-small-repaired.rdf";
//		String outputPath="Results/Simple-5-Interactive-asmov-fma-nci-small.rdf";
		
		
//		String sourcePath  = "testdata/largeBio/oaei_FMA_small_overlapping_nci.owl";
//		String targetPath  = "testdata/largeBio/oaei_NCI_small_overlapping_fma.owl";
//		String alignPath = "alignment4Experiment/FCA_Map-FMA_small-NCI_small.rdf";
//		//String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_mappings_with_flagged_repairs.rdf";
//		//String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_mappings_standard.rdf";
//		String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_reference.rdf";
//		//String outputPath="Results/One2One/Interactive-FCA_Map-fma-nci-small.rdf";
//		//String outputPath="Results/ChristianTool_new/Interactive-FCA_Map-fma-nci-small.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/Simple-Interactive-FCA_Map-fma-nci-small.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/FCA_Map-fma-nci-small.rdf";
//		//String outputPath="Results/Interactive-FCA_Map-fma-nci-small-repaired.rdf";
//		//String outputPath="Results/Simple-Interactive-FCA_Map-fma-nci-small.rdf";
//		String outputPath="Results/Simple-5-Interactive-FCA_Map-fma-nci-small.rdf";
		
//		String sourcePath  = "testdata/largeBio/oaei_FMA_small_overlapping_nci.owl";
//		String targetPath  = "testdata/largeBio/oaei_NCI_small_overlapping_fma.owl";
//		String alignPath = "alignment4Experiment/LogMapLite-largebio-fma_nci_small.rdf";
//		//String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_mappings_with_flagged_repairs.rdf";
//		String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_reference.rdf";
//		//String outputPath="Results/One2One/Interactive-LogMapLite-fma-nci-small.rdf";
//		//String outputPath="Results/ChristianTool_new/Interactive-LogMapLite-fma-nci-small.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/Simple-Interactive-LogMapLite-fma-nci-small.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/LogMapLite-fma-nci-small.rdf";
//		String outputPath="Results/Simple-5-Interactive-LogMapLite-fma-nci-small-repaired.rdf";
		
		
//		String sourcePath  = "testdata/largeBio/oaei_FMA_small_overlapping_nci.owl";
//		String targetPath  = "testdata/largeBio/oaei_NCI_small_overlapping_fma.owl";
//		String alignPath = "alignment4Experiment/AML_M-largebio-fma_nci_small.rdf";
//		//String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_mappings_with_flagged_repairs.rdf";
//		//String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_mappings_standard.rdf";
//		String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_reference.rdf";
//		//String outputPath="Results/One2One/Interactive-AML_M-fma-nci-small.rdf";
//		//String outputPath="Results/ChristianTool_new/Interactive-AML_M-fma-nci-small.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/Simple-Interactive-AML_M-fma-nci-small.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/Interactive-AML_M-fma-nci-small.rdf";
//		//String outputPath="Results/Interactive-AML_M-fma-nci-small-repaired.rdf";
//		String outputPath="Results/Simple-5-2-Interactive-AML_M-fma-nci-small.rdf";
		
		//测试大规模的情况是否可行
//		String sourcePath  = "testdata/largeBio/oaei_FMA_whole_ontology.owl";
//		String targetPath  = "testdata/largeBio/oaei_NCI_whole_ontology.owl";
//		String alignPath = "alignment4Experiment/LogMapLite-largebio-fma_nci_whole.rdf";
//		String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_reference.rdf";
//		//String outputPath="Results/One2One/Interactive-LogMapLite-fma-nci-whole.rdf";
//		//String outputPath="Results/ChristianTool_new/Interactive-LogMapLite-fma-nci-whole.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/Simple-Interactive-LogMapLite-fma-nci-whole.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/Interactive-LogMapLite-fma-nci-whole.rdf";
//		String outputPath="Results/Simple-5-2-Interactive-LogMapLite-fma-nci-whole.rdf";
		
//		String sourcePath  = "testdata/largeBio/oaei_FMA_whole_ontology.owl";
//		String targetPath  = "testdata/largeBio/oaei_NCI_whole_ontology.owl";
//		String alignPath = "alignment4Experiment/AML_M-FMA-NCI-whole.rdf";
//		String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_reference.rdf";
//		//String outputPath="Results/One2One/Interactive-AML_M-fma-nci-whole.rdf";
//		//String outputPath="Results/ChristianTool_new/Interactive-AML_M-fma-nci-whole.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/Simple-Interactive-AML_M-fma-nci-whole.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/Interactive-AML_M-fma-nci-whole.rdf";
//		String outputPath="Results/Simple-2-Interactive-AML_M-fma-nci-whole.rdf";
		

		
		
		
//		String sourcePath  = "testdata/largeBio/oaei_FMA_small_overlapping_snomed.owl";
//		String targetPath  = "testdata/largeBio/oaei_SNOMED_small_overlapping_fma.owl";
//		String alignPath = "alignment4Experiment/FCA_Map-FMA_small-SNOMED_small.rdf";
//		String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_mappings_with_flagged_repairs.rdf";
//		//String outputPath="Results/One2One/FCA_Map-fma-snomed-small.rdf";
//		//String outputPath="Results/ChristianTool_new/FCA_Map-fma-snomed-small.rdf";
//		//String outputPath="Results/StructureImpactor_Complete/FCA_Map-fma-snomed-small.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/FCA_Map-fma-snomed-small.rdf";
//		String outputPath="Results/Simple-Interactive-FCA_Map-fma-snomed-small.rdf";
		
//		String sourcePath  = "testdata/largeBio/oaei_SNOMED_small_overlapping_nci.owl";
//		String targetPath  = "testdata/largeBio/oaei_NCI_small_overlapping_snomed.owl";
//		String alignPath = "alignment4Experiment/FCA_Map-SNOMED_small-NCI_small.rdf";
//		String referencePath   = "testdata/largeBio/oaei_FMA2NCI_UMLS_mappings_with_flagged_repairs.rdf";
//		//String outputPath="Results/One2One/FCA_Map-snomed-nci-small.rdf";
//		//String outputPath="Results/ChristianTool_new/FCA_Map-snomed-nci-small.rdf";
//		String outputPath="Results/StructureImpactor_Complete/FCA_Map-snomed-nci-small.rdf";
//		//String outputPath="Results/OptimalImpactor_Complete/FCA_Map-snomed-nci-small.rdf";
		
		
		
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