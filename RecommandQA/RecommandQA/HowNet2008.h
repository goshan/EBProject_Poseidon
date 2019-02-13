/*  Copyright (c) 1999 - 2008 KEENAGE.com, Dong Zhendong & Dong Qiang. All Rights Reserved
/*
/*	FileName: HowNet2008.h
/*	Abstract: Header file of HowNet2008_Api for users
/*
/*	Version: V2008-1.0
/*	
*/
#ifdef HOWNET_API_EXPORTS
#define HOWNET_API __declspec(dllexport)
#else
#define HOWNET_API __declspec(dllimport)
#endif

#include <windows.h>

//	����ȡ�ü�¼���ĸ�����
#define	HOWNET_ITEM_ID_N_O						0x01		//	ȡ��¼��
#define	HOWNET_ITEM_ID_W_C						0x02		//	ȡ���Ĵ���
#define	HOWNET_ITEM_ID_G_C						0x03		//	ȡ���Ĵ���Ĵ���
#define	HOWNET_ITEM_ID_S_C						0x04		//	ȡ���Ĵ�������
#define	HOWNET_ITEM_ID_E_C						0x05		//	ȡ���Ĵ��������
#define	HOWNET_ITEM_ID_W_E						0x06		//	ȡӢ�Ĵ���
#define	HOWNET_ITEM_ID_G_E						0x07		//	ȡӢ�Ĵ���Ĵ���
#define	HOWNET_ITEM_ID_S_E						0x08		//	ȡӢ�Ĵ�������
#define	HOWNET_ITEM_ID_E_E						0x09		//	ȡ���Ĵ��������
#define	HOWNET_ITEM_ID_DEF						0x0A		//	ȡ�����
#define	HOWNET_ITEM_ID_P_Y						0x0B		//	ȡƴ��
#define	HOWNET_ITEM_ID_ALL						0x0C		//	ȡ������¼

#define	HOWNET_ITEM_ID_HYP						0x10		//	ȡ��������������λ
#define	HOWNET_ITEM_ID_ATT						0x11		//	���������ֵ����ȡ������ֵ������������
#define	HOWNET_ITEM_ID_VAL						0x12		//	�������������ȡ�������������������ֵ

//	�����������
#define	HOWNET_LANGUAGE_CHINESE					0x00		//	����
#define	HOWNET_LANGUAGE_ENGLISH					0x01		//	Ӣ��
#define	HOWNET_LANGUAGE_FEATURE					0x09		//	KDML

//	�����ѯ�ķ�ʽ�����չؼ��ֲ��ң�
#define HowNet_SearchMode_Exact					0xff00		//	��ȷƥ��
#define HowNet_SearchMode_First					0xff01		//	����ƥ��
#define HowNet_SearchMode_Fuzzy					0xff02		//	ģ��ƥ��
#define HowNet_SearchMode_Fuzzy_Abbr			0xff03		//	ģ������ƥ��
#define HowNet_SearchMode_Fuzzy_Jump			0xff04		//	��Ծʽģ��ƥ��
#define HowNet_SearchMode_Last					0xff05		//	β��ƥ��

//	�����ѯ�ķ�ʽ�����������ϵ���ң�
#define HowNet_SearchMode_Antonym				0xff0a		//	����
#define HowNet_SearchMode_Converse				0xff0b		//	����
#define HowNet_SearchMode_Synonym				0xff0c		//	ͬ��
#define HowNet_SearchMode_SynClass				0xff0d		//	ͬ��
#define HowNet_SearchMode_Hyponym				0xff0f		//	��λ

//	�����ѯ�ķ�ʽ�������¼���ϵ���ң�
#define HowNet_SearchMode_AccordingTo			0x0002		//	����
#define HowNet_SearchMode_CoEvent				0x0003		//	��Ӧ֮�¼�
#define HowNet_SearchMode_ContentCompare		0x0004		//	�Ƚ�����
#define HowNet_SearchMode_ContentProduct		0x0005		//	���ݳ�Ʒ
#define HowNet_SearchMode_DurationAfterEvent	0x0006		//	����ʱ��
#define HowNet_SearchMode_DurationBeforeEvent	0x0007		//	ǰ��ʱ��
#define HowNet_SearchMode_EventProcess			0x0008		//	�¼�����
#define HowNet_SearchMode_HostOf				0x0009		//	֮����
#define HowNet_SearchMode_LocationFin			0x000a		//	�մ���
#define HowNet_SearchMode_LocationIni			0x000b		//	ԭ����
#define HowNet_SearchMode_LocationThru			0x000c		//	ͨ������
#define HowNet_SearchMode_MaterialOf			0x000d		//	֮����
#define HowNet_SearchMode_OfPart				0x000e		//	����
#define HowNet_SearchMode_PartOfTouch			0x000f		//	��������
#define HowNet_SearchMode_PatientAttribute		0x0010		//	��������
#define HowNet_SearchMode_PatientPart			0x0011		//	������λ����
#define HowNet_SearchMode_PatientProduct		0x0012		//	��Ʒ����
#define	HowNet_SearchMode_PatientValue			0x0013		//	��������ֵ
#define HowNet_SearchMode_QuantityCompare		0x0014		//	�Ƚ���
#define HowNet_SearchMode_RelateTo				0x0015		//	���
#define HowNet_SearchMode_ResultContent			0x0016		//	�������
#define HowNet_SearchMode_ResultEvent			0x0017		//	����¼�
#define HowNet_SearchMode_ResultIsa				0x0018		//	�����ָ
#define HowNet_SearchMode_ResultWhole			0x0019		//	�������
#define HowNet_SearchMode_SincePeriod			0x001a		//	����ʱ��
#define HowNet_SearchMode_SincePoint			0x001b		//	����ʱ��
#define HowNet_SearchMode_SourceWhole			0x001c		//	��Դ����
#define HowNet_SearchMode_StateFin				0x001d		//	��״̬
#define HowNet_SearchMode_StateIni				0x001e		//	ԭ״̬
#define HowNet_SearchMode_TimeAfter				0x001f		//	֮��
#define HowNet_SearchMode_TimeBefore			0x0020		//	֮ǰ
#define HowNet_SearchMode_TimeFin				0x0021		//	��ֹʱ��
#define HowNet_SearchMode_TimeIni				0x0022		//	��ʼʱ��
#define HowNet_SearchMode_TimeRange				0x0023		//	ʱ��
#define HowNet_SearchMode_accompaniment			0x0024		//	����
#define HowNet_SearchMode_agent					0x0025		//	ʩ��
#define HowNet_SearchMode_and					0x0026		//	����
#define HowNet_SearchMode_aspect				0x0027		//	��
#define HowNet_SearchMode_belong				0x0028		//	����
#define HowNet_SearchMode_beneficiary			0x0029		//	������
#define HowNet_SearchMode_besides				0x002a		//	�ݽ�
#define HowNet_SearchMode_but					0x002b		//	����
#define HowNet_SearchMode_cause					0x002c		//	ԭ��
#define HowNet_SearchMode_coagent				0x002d		//	����ʩ��
#define HowNet_SearchMode_comment				0x002e		//	����
#define HowNet_SearchMode_concerning			0x002f		//	����
#define HowNet_SearchMode_concession			0x0030		//	�ò�
#define HowNet_SearchMode_condition				0x0031		//	����
#define HowNet_SearchMode_content				0x0032		//	����
#define HowNet_SearchMode_contrast				0x0033		//	������
#define HowNet_SearchMode_cost					0x0034		//	����
#define HowNet_SearchMode_degree				0x0035		//	�̶�
#define HowNet_SearchMode_descriptive			0x0036		//	��д��
#define HowNet_SearchMode_direction				0x0037		//	����
#define HowNet_SearchMode_distance				0x0038		//	����
#define HowNet_SearchMode_duration				0x0039		//	����ʱ��
#define HowNet_SearchMode_emphasis				0x003a		//	ǿ��
#define HowNet_SearchMode_except				0x003b		//	����
#define HowNet_SearchMode_existent				0x003c		//	������
#define HowNet_SearchMode_experiencer			0x003d		//	������
#define HowNet_SearchMode_frequency				0x003e		//	Ƶ��
#define HowNet_SearchMode_host					0x003f		//	����
#define HowNet_SearchMode_instrument			0x0040		//	����
#define HowNet_SearchMode_isa					0x0041		//	��ָ
#define HowNet_SearchMode_location				0x0042		//	����
#define HowNet_SearchMode_manner				0x0043		//	��ʽ
#define HowNet_SearchMode_material				0x0044		//	����
#define HowNet_SearchMode_means					0x0045		//	�ֶ�
#define HowNet_SearchMode_method				0x0046		//	����
#define HowNet_SearchMode_modifier				0x0047		//	����
#define HowNet_SearchMode_or					0x0048		//	����
#define HowNet_SearchMode_partner				0x0049		//	�����
#define HowNet_SearchMode_patient				0x004a		//	����
#define HowNet_SearchMode_possession			0x004b		//	ռ����
#define HowNet_SearchMode_possessor				0x004c		//	������
#define HowNet_SearchMode_purpose				0x004d		//	Ŀ��
#define HowNet_SearchMode_quantity				0x004e		//	����
#define HowNet_SearchMode_range					0x004f		//	����
#define HowNet_SearchMode_relevant				0x0050		//	��ϵ����
#define HowNet_SearchMode_restrictive			0x0051		//	�޶�
#define HowNet_SearchMode_result				0x0052		//	���
#define HowNet_SearchMode_scope					0x0053		//	��Χ
#define HowNet_SearchMode_sequence				0x0054		//	����
#define HowNet_SearchMode_source				0x0055		//	��Դ
#define HowNet_SearchMode_state					0x0056		//	״̬
#define HowNet_SearchMode_succeeding			0x0057		//	����
#define HowNet_SearchMode_target				0x0058		//	Ŀ��
#define HowNet_SearchMode_time					0x0059		//	ʱ��
#define HowNet_SearchMode_times					0x005a		//	����
#define HowNet_SearchMode_transition			0x005b		//	ת��
#define HowNet_SearchMode_whole					0x005c		//	����

//	֪��Taxonomy ��ÿ�����ı�־��Ϣ
#define	HOWNET_TAX_EVENT						0x00		//	֪���¼�����ԭ��
#define HOWNET_TAX_ENTITY						0x01		//	֪��ʵ������ԭ��
#define HOWNET_TAX_ATTRIBUTE					0x02		//	֪����������ԭ��
#define HOWNET_TAX_ATTRIBUTE_VALUE				0x03		//	֪������ֵ����ԭ��
#define HOWNET_TAX_2ND_FEATURE					0x04		//	֪����Ҫ������
#define HOWNET_TAX_PROPER_NOUN					0x05		//	֪��ר����ԭ��
#define HOWNET_TAX_SIGN							0x06		//	֪�����ű�
#define HOWNET_TAX_EVENT_ROLE					0x07		//	֪����̬��ɫ������������
#define HOWNET_TAX_PART_OF_SPEECH				0x08		//	֪���﷨������

//	֪��Taxonomy��ÿ����Ԫ������������
#define	HOWNET_TAX_NODE_SEME					0x01		//	Tax �ڵ����Ԫ����
#define	HOWNET_TAX_NODE_FRAM					0x02		//	Tax �ڵ�Ŀ������
#define	HOWNET_TAX_NODE_NOTE					0x03		//	Tax �ڵ��˵������

//	��ظ���еĲ㼶��
#define HOWNET_RELEVANCE_LEVEL1					0x00		//	��һ��
#define HOWNET_RELEVANCE_LEVEL2					0x01		//	�ڶ���
#define HOWNET_RELEVANCE_LEVEL3					0x02		//	������

//	���ݼ�¼��ͬ����������
struct S_SORT_ID
{
	BYTE	content[8];		//	��������
	BYTE	num;			//	���ݸ���
};

//	���ҵķ�ʽ
struct S_SEARCH_MODE
{
	BYTE	language;		//	��������
	WORD	mode;			//	����ģʽ
};


class HOWNET_API CHOWNET2008_API
{
public:
//---------------------------------------------------------------------------------
//	public
//---------------------------------------------------------------------------------
	bool	HowNet_Initial					( void );
	bool	HowNet_InitialSynAtnCon			( void );
	char*	HowNet_Get_ErrMsg				( void );
//---------------------------------------------------------------------------------
// Dictionary
//---------------------------------------------------------------------------------
	DWORD	HowNet_GetUnitNum				( void );
	DWORD*	HowNet_Get_SearchResult			( void );
	WORD	HowNet_Search_Keyword			( char* ApStr ,		S_SEARCH_MODE sSearchMode );
	WORD	HowNet_Search_Relation			( DWORD AdwUnitID , S_SEARCH_MODE sSearchMode );
	char*	HowNet_Get_Unit_Item			( const DWORD AdwUnitID , const BYTE AItemID , char* ApRlt );
	void	HowNet_Sort_Unit				( DWORD* base , long num , S_SORT_ID sSortID );
//---------------------------------------------------------------------------------
//	Taxonomy
//---------------------------------------------------------------------------------
	void	HowNet_GetOneNodeContent		( BYTE AnTree , WORD AnNode , BYTE AContentID , char* ApNodeContent );
	int		HowNet_Search_Node_In_Tax_Tree	( BYTE AnTree , const char *ApKeyword , BYTE* ApLayerPos = NULL );
	WORD	HowNet_Get_Sememe_Code			( const char *ApKeyword );
	char*	HowNet_Get_Sememe_String		( WORD AwSememeCode );
	WORD	HowNet_Get_Sememe_Hyp			( WORD AwSememeCode );
	BYTE	HowNet_Get_TreeID				( WORD AwSememeCode );
	WORD	HowNet_Get_Sememe_Location		( WORD AwSememeCode );
//---------------------------------------------------------------------------------
// Relevance
//---------------------------------------------------------------------------------
	WORD	HowNet_Get_Concept_Relevance	( DWORD AdwUnitID , BYTE ALanguageID , BYTE ALevelID );
//---------------------------------------------------------------------------------
//	Similarity
//---------------------------------------------------------------------------------
	BYTE	HowNet_Get_Smemme_Distance		( WORD AwSememeCodeA, WORD AwSememeCodeB );
	double	HowNet_Get_Concept_Similarity	( DWORD AdwUnitIDA, DWORD AdwUnitIDB, float AfA = 1.6, float AfB1 = 0.1, float AfB2 = 0.1, float AfB3 = 0.7, float AfB4 = 0.1 );

};


