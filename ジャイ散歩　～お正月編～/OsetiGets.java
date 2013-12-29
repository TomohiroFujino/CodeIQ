package charenge2;
/**
 * CodeIQ用クラス
 * @author Fujino
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
public  class OsetiGets{

   //家Aからのルート用マップ変数
   private final Map<String,Integer> A  =new Hashtable<String,Integer>(){
   	 {put("A",0);put("B",10);put("C",20);put("D",7);put("E",15);put("F",24);put("G",18);put("H",22);}};
   //家Bからのルート用マップ変数
   private Map<String,Integer> B  =new Hashtable<String,Integer>(){
        {put("A",10);put("B",0);put("C",11);put("D",5);put("E",8);put("F",20);put("G",21);put("H",15);}};
   //家Cからのルート用マップ変数
   private final Map<String,Integer> C  =new Hashtable<String,Integer>(){
            {put("A",20);put("B",11);put("C",0);put("D",10);put("E",12);put("F",6);put("G",25);put("H",20);}};
   //家Dからのルート用マップ変数
   private final Map<String,Integer> D  =new Hashtable<String,Integer>(){
        {put("A",7);put("B",5);put("C",10);put("D",0);put("E",9);put("F",17);put("G",15);put("H",13);}};
   //家Eからのルート用マップ変数
   private final Map<String,Integer> E  =new Hashtable<String,Integer>(){
        {put("A",15);put("B",8);put("C",12);put("D",9);put("E",0);put("F",7);put("G",10);put("H",6);}};
   //家Fからのルート用マップ変数
   private final Map<String,Integer> F  =new Hashtable<String,Integer>(){
             {put("A",24);put("B",20);put("C",6);put("D",17);put("E",7);put("F",0);put("G",14);put("H",10);}};
   //家Gからのルート用マップ変数
   private final Map<String,Integer> G  =new Hashtable<String,Integer>(){
             {put("A",18);put("B",21);put("C",25);put("D",15);put("E",10);put("F",14);put("G",0);put("H",5);}};
   //家Gからのルート用マップ変数
   private final Map<String,Integer> H  =new Hashtable<String,Integer>(){
             {put("A",22);put("B",15);put("C",20);put("D",13);put("E",6);put("F",10);put("G",5);put("H",0);}};
   //すべてのルートを格納したマップ変数
   private final Map<String, Map<String,Integer>> Travel_Time = new Hashtable<String,Map<String,Integer>>(){
             {put("A",A);put("B",B);put("C",C);put("D",D);put("E",E);put("F",F);put("G",G);put("H",H);}};

   //おせち集めをするキャラクターの名前用の定数
   private final String OSETI_GETTER_CHARA="jyaio";
   //現在地用の変数(初期値：家A)
   private String nowPlace;
   //出発地点用の定数
   private String START_PLACE="A";
   //次のルート用の変数
   private String nextPlace=null;
   //おせち集めの開始時刻(分)用の定数
   private final int START_TIME = 15 * 60;
   //おせち集めにかかった時間(分)用の変数
   private int totalTime=0;
   //おせち集めのルート用変数
   private List<String> root = new ArrayList<String>();

   public String getOSsetiGetteCharar(){return this.OSETI_GETTER_CHARA;}

   /**
    * おせち集め(一か所)を行うメソッド
    * @param travelTime 家○からのルート用マップ
    */
   public int getOseti(Map<String,Integer> travelTime){
     int min=0;
     //引数の家ルート要素数繰り返す
     for (Map.Entry<String,Integer> root : travelTime.entrySet()){
         if((min > root.getValue() && root.getValue()!=0) || min ==0){
           min = root.getValue();
           nextPlace = root.getKey();
         }
     }
     //スタート地点用の要素を最後に処理する為のif文
     if(!nowPlace.equals(START_PLACE) || this.Travel_Time.size()==1){
       removeRoot(nowPlace);
       this.root.add(nowPlace);
     }
     nowPlace = nextPlace;
	 return min;
   }
   /**
    * おせち集めを行った家の要素を、すべてのマップから削除するメソッド
    * @param root 家○からのルート用マップキー
    */
   public void removeRoot(String root){
	   Travel_Time.remove(root);
	   A.remove(root);
	   B.remove(root);
	   C.remove(root);
	   D.remove(root);
	   E.remove(root);
	   F.remove(root);
	   G.remove(root);
	   H.remove(root);
   }
   //おせち集め(すべて)を行うメソッド
   public final void getsOseti(){
	  //現在地の初期値設定
	  this.nowPlace = this.START_PLACE;
	  //初期値用のgetOseti(スタート地点の要素を最初に削除しない為)
	  this.totalTime += getOseti(Travel_Time.get(START_PLACE));
	  //ルート要素数文繰り返す
	  do{

		  this.totalTime += getOseti(Travel_Time.get(this.nowPlace));

	  }while(Travel_Time.size()!=0);

   }

   //おせち集めが完了した時刻を返すメソッド
   public String getGoalTime(){
	   int i = (START_TIME + this.totalTime) * 100;
	   String h = Integer.toString(i/6000);
	   String m =  Integer.toString((i%6000) /60);
	   //return str;
	   return h + "時" + m + "分" ;
   }
   //おせち集めのルートを返すメソッド
   public String getRoot(){
	   String result = "";
	   for(String house:this.root){

        result+=house;
        if(!house.equals(root.get(root.size()-1))){
    	   result+="→";
	    }
       }
	   return result;
   }

    public static void main(String[] args){
      OsetiGets jyaio = new OsetiGets();
      jyaio.getsOseti();
      System.out.println(jyaio.getOSsetiGetteCharar() + "が、おせち集めに回ったルート");
      System.out.println(jyaio.getRoot());
      System.out.println("ジャイオが、おせち集めから帰ってきた時間");
      System.out.println(jyaio.getGoalTime());
    }
}
