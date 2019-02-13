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

//	定义取得记录的哪个部分
#define	HOWNET_ITEM_ID_N_O						0x01		//	取记录号
#define	HOWNET_ITEM_ID_W_C						0x02		//	取中文词语
#define	HOWNET_ITEM_ID_G_C						0x03		//	取中文词语的词性
#define	HOWNET_ITEM_ID_S_C						0x04		//	取中文词语的情感
#define	HOWNET_ITEM_ID_E_C						0x05		//	取中文词语的例子
#define	HOWNET_ITEM_ID_W_E						0x06		//	取英文词语
#define	HOWNET_ITEM_ID_G_E						0x07		//	取英文词语的词性
#define	HOWNET_ITEM_ID_S_E						0x08		//	取英文词语的情感
#define	HOWNET_ITEM_ID_E_E						0x09		//	取中文词语的例子
#define	HOWNET_ITEM_ID_DEF						0x0A		//	取概念定义
#define	HOWNET_ITEM_ID_P_Y						0x0B		//	取拼音
#define	HOWNET_ITEM_ID_ALL						0x0C		//	取整个记录

#define	HOWNET_ITEM_ID_HYP						0x10		//	取概念的类的所有上位
#define	HOWNET_ITEM_ID_ATT						0x11		//	如果是属性值类概念，取该属性值所隶属的属性
#define	HOWNET_ITEM_ID_VAL						0x12		//	如果是属性类概念，取该属性下面的所有属性值

//	定义查找语言
#define	HOWNET_LANGUAGE_CHINESE					0x00		//	中文
#define	HOWNET_LANGUAGE_ENGLISH					0x01		//	英文
#define	HOWNET_LANGUAGE_FEATURE					0x09		//	KDML

//	定义查询的方式（按照关键字查找）
#define HowNet_SearchMode_Exact					0xff00		//	精确匹配
#define HowNet_SearchMode_First					0xff01		//	首字匹配
#define HowNet_SearchMode_Fuzzy					0xff02		//	模糊匹配
#define HowNet_SearchMode_Fuzzy_Abbr			0xff03		//	模糊缩略匹配
#define HowNet_SearchMode_Fuzzy_Jump			0xff04		//	跳跃式模糊匹配
#define HowNet_SearchMode_Last					0xff05		//	尾字匹配

//	定义查询的方式（按照语义关系查找）
#define HowNet_SearchMode_Antonym				0xff0a		//	反义
#define HowNet_SearchMode_Converse				0xff0b		//	对义
#define HowNet_SearchMode_Synonym				0xff0c		//	同义
#define HowNet_SearchMode_SynClass				0xff0d		//	同类
#define HowNet_SearchMode_Hyponym				0xff0f		//	下位

//	定义查询的方式（按照事件关系查找）
#define HowNet_SearchMode_AccordingTo			0x0002		//	根据
#define HowNet_SearchMode_CoEvent				0x0003		//	对应之事件
#define HowNet_SearchMode_ContentCompare		0x0004		//	比较内容
#define HowNet_SearchMode_ContentProduct		0x0005		//	内容成品
#define HowNet_SearchMode_DurationAfterEvent	0x0006		//	后延时段
#define HowNet_SearchMode_DurationBeforeEvent	0x0007		//	前耗时段
#define HowNet_SearchMode_EventProcess			0x0008		//	事件过程
#define HowNet_SearchMode_HostOf				0x0009		//	之宿主
#define HowNet_SearchMode_LocationFin			0x000a		//	终处所
#define HowNet_SearchMode_LocationIni			0x000b		//	原处所
#define HowNet_SearchMode_LocationThru			0x000c		//	通过处所
#define HowNet_SearchMode_MaterialOf			0x000d		//	之材料
#define HowNet_SearchMode_OfPart				0x000e		//	部分
#define HowNet_SearchMode_PartOfTouch			0x000f		//	触及部件
#define HowNet_SearchMode_PatientAttribute		0x0010		//	受事属性
#define HowNet_SearchMode_PatientPart			0x0011		//	部件部位受事
#define HowNet_SearchMode_PatientProduct		0x0012		//	成品受事
#define	HowNet_SearchMode_PatientValue			0x0013		//	受事属性值
#define HowNet_SearchMode_QuantityCompare		0x0014		//	比较量
#define HowNet_SearchMode_RelateTo				0x0015		//	相关
#define HowNet_SearchMode_ResultContent			0x0016		//	结果内容
#define HowNet_SearchMode_ResultEvent			0x0017		//	结果事件
#define HowNet_SearchMode_ResultIsa				0x0018		//	结果类指
#define HowNet_SearchMode_ResultWhole			0x0019		//	结果整体
#define HowNet_SearchMode_SincePeriod			0x001a		//	起自时段
#define HowNet_SearchMode_SincePoint			0x001b		//	起自时点
#define HowNet_SearchMode_SourceWhole			0x001c		//	来源整体
#define HowNet_SearchMode_StateFin				0x001d		//	终状态
#define HowNet_SearchMode_StateIni				0x001e		//	原状态
#define HowNet_SearchMode_TimeAfter				0x001f		//	之后
#define HowNet_SearchMode_TimeBefore			0x0020		//	之前
#define HowNet_SearchMode_TimeFin				0x0021		//	终止时间
#define HowNet_SearchMode_TimeIni				0x0022		//	起始时间
#define HowNet_SearchMode_TimeRange				0x0023		//	时距
#define HowNet_SearchMode_accompaniment			0x0024		//	伴随
#define HowNet_SearchMode_agent					0x0025		//	施事
#define HowNet_SearchMode_and					0x0026		//	并列
#define HowNet_SearchMode_aspect				0x0027		//	体
#define HowNet_SearchMode_belong				0x0028		//	归属
#define HowNet_SearchMode_beneficiary			0x0029		//	受益者
#define HowNet_SearchMode_besides				0x002a		//	递进
#define HowNet_SearchMode_but					0x002b		//	但是
#define HowNet_SearchMode_cause					0x002c		//	原因
#define HowNet_SearchMode_coagent				0x002d		//	合作施事
#define HowNet_SearchMode_comment				0x002e		//	评论
#define HowNet_SearchMode_concerning			0x002f		//	关于
#define HowNet_SearchMode_concession			0x0030		//	让步
#define HowNet_SearchMode_condition				0x0031		//	条件
#define HowNet_SearchMode_content				0x0032		//	内容
#define HowNet_SearchMode_contrast				0x0033		//	参照体
#define HowNet_SearchMode_cost					0x0034		//	代价
#define HowNet_SearchMode_degree				0x0035		//	程度
#define HowNet_SearchMode_descriptive			0x0036		//	描写体
#define HowNet_SearchMode_direction				0x0037		//	方向
#define HowNet_SearchMode_distance				0x0038		//	距离
#define HowNet_SearchMode_duration				0x0039		//	进程时段
#define HowNet_SearchMode_emphasis				0x003a		//	强调
#define HowNet_SearchMode_except				0x003b		//	除了
#define HowNet_SearchMode_existent				0x003c		//	存现体
#define HowNet_SearchMode_experiencer			0x003d		//	经验者
#define HowNet_SearchMode_frequency				0x003e		//	频率
#define HowNet_SearchMode_host					0x003f		//	宿主
#define HowNet_SearchMode_instrument			0x0040		//	工具
#define HowNet_SearchMode_isa					0x0041		//	类指
#define HowNet_SearchMode_location				0x0042		//	处所
#define HowNet_SearchMode_manner				0x0043		//	方式
#define HowNet_SearchMode_material				0x0044		//	材料
#define HowNet_SearchMode_means					0x0045		//	手段
#define HowNet_SearchMode_method				0x0046		//	方法
#define HowNet_SearchMode_modifier				0x0047		//	描述
#define HowNet_SearchMode_or					0x0048		//	或者
#define HowNet_SearchMode_partner				0x0049		//	相伴体
#define HowNet_SearchMode_patient				0x004a		//	受事
#define HowNet_SearchMode_possession			0x004b		//	占有物
#define HowNet_SearchMode_possessor				0x004c		//	领有者
#define HowNet_SearchMode_purpose				0x004d		//	目的
#define HowNet_SearchMode_quantity				0x004e		//	数量
#define HowNet_SearchMode_range					0x004f		//	幅度
#define HowNet_SearchMode_relevant				0x0050		//	关系主体
#define HowNet_SearchMode_restrictive			0x0051		//	限定
#define HowNet_SearchMode_result				0x0052		//	结果
#define HowNet_SearchMode_scope					0x0053		//	范围
#define HowNet_SearchMode_sequence				0x0054		//	次序
#define HowNet_SearchMode_source				0x0055		//	来源
#define HowNet_SearchMode_state					0x0056		//	状态
#define HowNet_SearchMode_succeeding			0x0057		//	接续
#define HowNet_SearchMode_target				0x0058		//	目标
#define HowNet_SearchMode_time					0x0059		//	时间
#define HowNet_SearchMode_times					0x005a		//	动量
#define HowNet_SearchMode_transition			0x005b		//	转折
#define HowNet_SearchMode_whole					0x005c		//	整体

//	知网Taxonomy 中每棵树的标志信息
#define	HOWNET_TAX_EVENT						0x00		//	知网事件类义原表
#define HOWNET_TAX_ENTITY						0x01		//	知网实体类义原表
#define HOWNET_TAX_ATTRIBUTE					0x02		//	知网属性类义原表
#define HOWNET_TAX_ATTRIBUTE_VALUE				0x03		//	知网属性值类义原表
#define HOWNET_TAX_2ND_FEATURE					0x04		//	知网次要特征表
#define HOWNET_TAX_PROPER_NOUN					0x05		//	知网专有义原表
#define HOWNET_TAX_SIGN							0x06		//	知网符号表
#define HOWNET_TAX_EVENT_ROLE					0x07		//	知网动态角色与语用特征表
#define HOWNET_TAX_PART_OF_SPEECH				0x08		//	知网语法特征表

//	知网Taxonomy中每个义元所包含的内容
#define	HOWNET_TAX_NODE_SEME					0x01		//	Tax 节点的义元内容
#define	HOWNET_TAX_NODE_FRAM					0x02		//	Tax 节点的框架内容
#define	HOWNET_TAX_NODE_NOTE					0x03		//	Tax 节点的说明内容

//	相关概念场中的层级数
#define HOWNET_RELEVANCE_LEVEL1					0x00		//	第一层
#define HOWNET_RELEVANCE_LEVEL2					0x01		//	第二层
#define HOWNET_RELEVANCE_LEVEL3					0x02		//	第三层

//	根据记录不同的内容排序
struct S_SORT_ID
{
	BYTE	content[8];		//	排序内容
	BYTE	num;			//	内容个数
};

//	查找的方式
struct S_SEARCH_MODE
{
	BYTE	language;		//	查找语言
	WORD	mode;			//	查找模式
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


