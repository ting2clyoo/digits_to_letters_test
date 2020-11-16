package com.ct;


import java.util.*;


/**
 * @author Acer
 */
public class DigitsToLettersTest {

    public static HashMap<String, List<String>> digitsMap;

    public static void main( String[] args ){
        initDigitsMap();//Initialization method
        digitsToLetters();
    }

    /**
     * Initialization of number and letter mapping data
     */
    public static void initDigitsMap(){
        digitsMap=new HashMap<>();
        digitsMap.put("0", Arrays.asList(""));
        digitsMap.put("1",Arrays.asList(""));
        digitsMap.put("2",Arrays.asList("A","B","C"));
        digitsMap.put("3",Arrays.asList("D","E","F"));
        digitsMap.put("4",Arrays.asList("G","H","I"));
        digitsMap.put("5",Arrays.asList("J","K","L"));
        digitsMap.put("6",Arrays.asList("M","N","O"));
        digitsMap.put("7",Arrays.asList("P","Q","R","S"));
        digitsMap.put("8",Arrays.asList("T","U","V"));
        digitsMap.put("9",Arrays.asList("W","X","Y","Z"));

    }

    /**
     * Convert numbers to an array of letters
     */
    public static void digitsToLetters(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please input digits from 0 to 9:");
        //输入按键数字1-9
        String digits="";
        while(true){
            digits= input.next();
            if(!digits.matches("[0-9]{1,2}")){
                System.out.println("Illegal input! Only 0-99 numbers can be entered. Please re-enter：");
            }else{
                break;
            }
        }

        StringBuilder arrInput=new StringBuilder("Input:arr[] ={");
        String[] arrStr=digits.split("");
        List<String[]> dataList=new ArrayList<>();

        for(int i=0;i<arrStr.length;i++){
            arrInput.append(arrStr[i]);
            if(i<arrStr.length-1){
                arrInput.append(",");
            }
            //First add the data from multiple lists to the same collection as the data source
            List<String> lettersList=digitsMap.get(arrStr[i]);
            //A collection without data cannot be forcibly converted to an array
            if(lettersList.size()>0){
                String[] letterArr= (String[]) lettersList.toArray();
                dataList.add(letterArr);
            }
        }
        arrInput.append("}");
        //Recursively realizes the permutation and combination of multiple arrays, and returns the final permutation set
        List<String[]> resultList= makeupLetters(dataList,0,null);
        //Print input
        System.out.println(arrInput.toString());
        System.out.print("Output:");
        //Print out the result of permutation and combination
        for(int i=0;i<resultList.size();i++){
            String[] letterArr=resultList.get(i);
            System.out.print(" ");
            for(String s: letterArr){
                System.out.print(s);
            }
        }
    }

    /**
     * Recursive implementation of multiple array permutation and combination
     * @param dataList
     * @param index
     * @param resultList
     * @return
     */
    private static List<String[]> makeupLetters(List<String[]> dataList, int index, List<String[]> resultList){
        if(index==dataList.size()){
            return resultList;
        }

        List<String[]> resultList0=new ArrayList<String[]>();
        //The first column array adds as many permutation data as the number of letters by default
        if(index==0){
            String[] dataArr=dataList.get(0);
            for(String s : dataArr){
                resultList0.add(new String[]{s});
            }
        }else{
            String[] dataArr=dataList.get(index);
            for(String[] dataArr0: resultList){
                for(String s : dataArr){
                    //Copy array and expand new elements
                    String[] dataArrCopy=new String[dataArr0.length+1];
                    System.arraycopy(dataArr0, 0, dataArrCopy, 0, dataArr0.length);
                    dataArrCopy[dataArrCopy.length-1]=s;
                    //Append to result set
                    resultList0.add(dataArrCopy);
                }
            }
        }
        return makeupLetters(dataList,++index,resultList0);
    }

}
