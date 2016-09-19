import java.util.*;
import java.io.*;
public class function {
 

 
    public static void main(String[] args) {
      String fun;
      String temp;
      Scanner in = new Scanner(System.in);
      System.out.println("Do you want to us the normal distribution?");
      fun = in.nextLine();
      if(fun.equals("y")){
         fun = "(1/(s*((2*p)^h)))*e^(n*(((x-u)^2)/(2*s^2)))";
      }
      else{
       System.out.println("Enter the funtion you want to create use only *, /, - , +, ^,(,) syombols.");
          fun = in.nextLine();
      }
          System.out.println("you entered " + fun + " do you want to run it y/n");
          temp = in.nextLine();
      if( temp.equals("y")){
         //(1/(s*((2*p)^h)))*e^(n*(((x-u)^2)/(2*s^2)))
         double me = RunFunction(fun);
      }
      else{
         //System.out.println("no");
      }
   }
   public static double RunFunction(String fun){
       String var = null;
       String var2 = null;
       String var3 = null;
       int numvar = 0;
       int t = 0;
       ArrayList<Double> prens = new ArrayList<Double>();
       ArrayList<Double> mul_div = new ArrayList<Double>();
       ArrayList<Double> plus_minus = new ArrayList<Double>();
       ArrayList<Double> variable = new ArrayList<Double>();
       ArrayList<Double> exp = new ArrayList<Double>();
       Scanner in = new Scanner(System.in);
       System.out.println("Which variable do you to change, you can enter up to three enter done when you entered the last variable (devlopment note this is for a future function press d for now)"); 
       var = in.nextLine();
       if(var.equals("done")){
         t = 3;
         var = null;
       }
       else{
         t++;
         numvar++;
       }
       if ( t < 3){
         System.out.println("Do you want to enter another variable, you can enter up to three enter done when you entered the last variable"); 
         var2 = in.nextLine();
         if(var2.equals("done")){
            t = 3;
            var2 = null;
         }
         else{
            t++;
            numvar++;
          }
         if(t < 3){
            System.out.println("Do you want to enter another variable, you can enter up to three enter done when you entered the last variable"); 
             var3 = in.nextLine();
             if(var3.equals("done")){
                var3 = null;
              }
              else{
               numvar++;
              }
          }
        }
        for( t = 0; t < fun.length(); t++){
         String i = Character.toString(fun.charAt(t));
         if(!(i.equals( ")")) && !(i.equals( "(")) && !(i.equals( "^")) && !(i.equals( var)) && !(i.equals(var2)) && !(i.equals(var3)) && !(i.equals(var3)) && !(i.equals( "+")) && !( i.equals("-"))&& !(i.equals( "*")) && !( i.equals("/"))){
            System.out.println("What value do you want (" + i + ") to be?");
            variable.add(in.nextDouble());
         }
        }
        prens = pren(fun, prens, variable, var, var2, var3);
       exp = power(fun,prens, exp, variable, var, var2, var3);
       mul_div = time_div(fun, prens, exp,mul_div,variable,var,var2,var3);
       plus_minus = addition_subtraction(fun, exp, prens ,mul_div, plus_minus, variable, var, var2, var3);
       double Total = 0.0;
       if(plus_minus.size() > 0){
         Total = plus_minus.get(plus_minus.size() -1);
       }
       else if(mul_div.size() > 0){
         Total = mul_div.get(mul_div.size()- 1);
       }
       else if (exp.size() > 0){
         Total = exp.get(exp.size() -1);
       }
       else{
         Total = prens.get(prens.size()-1);
       }
       System.out.println(Total);
        return Total;
    }
    
    
   public static ArrayList<Double> pren(String fun, ArrayList<Double> prens, ArrayList<Double> varablies, String var, String var2, String var3){
      int t;
      int prencount = 0;
      int varcount = 0;
      int found = 0;
      ArrayList<Integer> pos = new ArrayList<Integer>();
      ArrayList<Double>tempvar = new ArrayList<Double>();
      for(t = 0; t< fun.length(); t++){
         String i = Character.toString(fun.charAt(t));
         if(i.equals("(")){
            pos.add(t);
         }
         else if (i.equals(")")){
            String sub = fun.substring(pos.get(pos.size()-1)+1, t);
            pos.remove(pos.size()-1);
            int tt;
            int minusvar = 0;
            for(tt = 0; tt<sub.length(); tt++){
            String ii = Character.toString(sub.charAt(tt));
               if(!(ii.equals( "(")) && !(ii.equals( ")")) && !(ii.equals( "^")) && !(ii.equals( var)) && !(ii.equals(var2)) && !(ii.equals(var3)) && !(ii.equals(var3)) && !(ii.equals( "+")) && !( ii.equals("-"))&& !(ii.equals( "*")) && !( ii.equals("/"))){
                  minusvar++;
                  tempvar.add(0, varablies.get(varcount - minusvar));
               }
            }
            prens.add(RunPrens(sub, tempvar, var, var2, var3));
            tempvar.clear();
          }
           else if(!(i.equals( "(")) && !(i.equals( ")")) &&!(i.equals( "^")) && !(i.equals( var)) && !(i.equals(var2)) && !(i.equals(var3)) && !(i.equals(var3)) && !(i.equals( "+")) && !( i.equals("-"))&& !(i.equals( "*")) && !( i.equals("/"))){
              varcount++;
            }
       }
        return prens; 
   }
   
    public static double RunPrens(String fun, ArrayList<Double> varablies, String var, String var2, String var3){
       ArrayList<Double> prens = new ArrayList<Double>();
       ArrayList<Double> mul_div = new ArrayList<Double>();
       ArrayList<Double> plus_minus = new ArrayList<Double>();
       ArrayList<Double> variable = new ArrayList<Double>();
       ArrayList<Double> exp = new ArrayList<Double>();
       prens = pren(fun, prens, varablies, var, var2, var3);
       exp = power(fun, prens, exp, varablies, var, var2, var3);
        mul_div = time_div(fun, prens, exp,mul_div,varablies,var,var2,var3);
       plus_minus = addition_subtraction(fun, exp, prens, mul_div, plus_minus, varablies, var, var2, var3);
       double Total;
       if(plus_minus.size() > 0){
         Total = plus_minus.get(plus_minus.size() -1);
       }
       else if(mul_div.size() > 0){
         Total = mul_div.get(mul_div.size()- 1);
       }
       else{
         Total = exp.get(exp.size() -1);
       }
        return Total;
   }
        
   public static ArrayList<Double> power(String fun,ArrayList<Double> pren, ArrayList<Double> exp, ArrayList<Double> varablies, String var, String var2, String var3){
      int t;
      int varcount = 0;
      int expcount = 0;
      int prencount = 0;
      int pfound = 0;
      int ptotal = 0;
      for(t = 0; t < fun.length(); t++){
         String i = Character.toString(fun.charAt(t));
         if(i.equals("(")){
            pfound = 1;
            ptotal++;
         }
         else if (i.equals(")")){
            prencount++;
            ptotal--;
            if(ptotal == 0){
            pfound = 0;
             }
         }
         if(i.equals("^") && pfound == 0){
          expcount++;
            Double temp1 = 0.0;
            Double temp2 = 0.0;
            if(Character.toString(fun.charAt(t-1)).equals(")")){
            temp1 = pren.get(prencount -1);
            temp2 = varablies.get(varcount);
            }
            else if(t-2 <0 || !(Character.toString(fun.charAt(t-2)).equals("^"))){
               temp1 = varablies.get(varcount  -2);
               temp2 = varablies.get(varcount);
               if(Character.toString(fun.charAt(t+1)).equals("(")){
                                 int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
                  temp2 = pren.get(temppren2-1);
               }
            }
            else if(Character.toString(fun.charAt(t-2)).equals("^")){
                 temp1 = exp.get(expcount -2);
                 temp2 =varablies.get(varcount);
                 if(Character.toString(fun.charAt(t+1)).equals("(")){
                                int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
            temp2 = pren.get(temppren2-1);  
                       }
            }
            Double tot = Math.pow(temp1, temp2);
            exp.add(tot);
         }
         else if(!(Character.toString(fun.charAt(t)).equals("(")) &&!(Character.toString(fun.charAt(t)).equals(")")) &&!(Character.toString(fun.charAt(t)).equals("*")) &&!(Character.toString(fun.charAt(t)).equals("/")) &&!(Character.toString(fun.charAt(t)).equals("+")) && !(Character.toString(fun.charAt(t)).equals("-"))){
            varcount++;
         }
     
    }
    return exp;
  }

    
    
   public static ArrayList<Double> time_div(String fun, ArrayList<Double> pren, ArrayList<Double> exp, ArrayList<Double> mul_div, ArrayList<Double> varablies, String var, String var2, String var3){
      int t;
      int varcount  =0;
      int prencount = 0;
      int pfound = 0;
      int Times_dived_count = 0;
      int expcount = 0;
      int ptotal = 0;
      for(t = 0; t < fun.length(); t++){
         String i = Character.toString(fun.charAt(t));
         if(i.equals("(")){
            pfound = 1;
            ptotal++;
         }
         else if (i.equals(")")){
            prencount++;
             ptotal--;
            if(ptotal == 0){
               pfound = 0;             }
         }

         if(i.equals("*") && pfound  == 0){
            Times_dived_count++;
          if(Character.toString(fun.charAt(t-1)).equals(")")){
            Double temp1 = pren.get(prencount -1);
            Double temp2 = 0.0;
            if(Character.toString(fun.charAt(t+1)).equals("(")){
               int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
               temp2 = pren.get(temppren2-1) ;
           }
            else if(t + 2 < fun.length() && Character.toString(fun.charAt(t+2)).equals("^")){
               temp2 = exp.get(expcount);
                  int z = t + 4;
                  int tempexpcount = expcount;
                  while(z < fun.length() && Character.toString(fun.charAt(z)).equals("^")){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z + 2;
                  }
              }
              else{
                   temp2 = varablies.get(varcount);
              }
              double tot = temp2 * temp1;
              mul_div.add(tot);
           }
             else if( t - 2 < 0 ||  (Character.toString(fun.charAt(t-2)).equals("+") ||Character.toString(fun.charAt(t-2)).equals("-"))){
            Double temp1 = varablies.get(varcount-1);
            Double temp2 = 0.0;
              if(Character.toString(fun.charAt(t+1)).equals("(")){
               int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
                  temp2 = pren.get(temppren2-1) ;
                    }
              else if(t + 2 < fun.length() && Character.toString(fun.charAt(t+2)).equals("^")){
                  temp2 = exp.get(expcount);
                  int z = t + 4;
                  int tempexpcount = expcount;
                  while(z < fun.length() && Character.toString(fun.charAt(z)).equals("^")){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z + 2;
                  }
              }
              else{
                   temp2 = varablies.get(varcount);
              }
               double tot = temp2 * temp1;
               mul_div.add(tot);
               //System.out.println(temp1 + " " + temp2);
             }
             else if (Character.toString(fun.charAt(t-2)).equals("*") ||Character.toString(fun.charAt(t-2)).equals("/")){
              Double temp1 = mul_div.get(mul_div.size() -1);
              Double temp2 = 0.0;
                          if(Character.toString(fun.charAt(t+1)).equals("(")){
                              int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1);
            }
              else if(t + 2 < fun.length() && Character.toString(fun.charAt(t+2)).equals("^")){
               temp2 = exp.get(expcount);
                                int z = t + 4;
                  int tempexpcount = expcount;
                  while(z < fun.length() && Character.toString(fun.charAt(z)).equals("^")){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z + 2;
                  }
              }
              else{
                   temp2 = varablies.get(varcount);
              }
               double tot = temp1 * temp2;
               mul_div.add(tot);
             }
             else if(Character.toString(fun.charAt(t-2)).equals("^")){
               Double temp1 = exp.get(expcount -1);
               Double temp2 = 0.0;
                          if(Character.toString(fun.charAt(t+1)).equals("(")){
                              int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1) ;           }
             else if( t + 2 < fun.length() && Character.toString(fun.charAt(t+2)).equals("^")){
                  temp2 = exp.get(expcount);
                                int z = t + 4;
                  int tempexpcount = expcount;
                  while(z < fun.length() && Character.toString(fun.charAt(z)).equals("^")){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z + 2;
                  }
              }
              else{
                   temp2 = varablies.get(varcount);
              }
              double tot = temp2 * temp1;
              mul_div.add(tot);
            }
          }
         else if(i.equals("/") && pfound == 0){
            Times_dived_count++;
                      if(Character.toString(fun.charAt(t-1)).equals(")")){
            Double temp1 = pren.get(prencount -1);
            Double temp2 = 0.0;
            if(Character.toString(fun.charAt(t+1)).equals("(")){
                              int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1)   ;         }
            else if(t + 2 < fun.length() && Character.toString(fun.charAt(t+2)).equals("^")){
               temp2 = exp.get(expcount);
                  int z = t + 4;
                  int tempexpcount = expcount;
                  while(z < fun.length() && Character.toString(fun.charAt(z)).equals("^")){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z + 2;
                  }
              }
              else{
                   temp2 = varablies.get(varcount);
              }
              double tot = temp1 / temp2;
              mul_div.add(tot);
           }
             else if( t - 2 < 0 ||  (Character.toString(fun.charAt(t-2)).equals("+") ||Character.toString(fun.charAt(t-2)).equals("-"))){
            Double temp1 = varablies.get(varcount-1);
            Double temp2 = 0.0;
              if(Character.toString(fun.charAt(t+1)).equals("(")){
               int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
                  temp2 = pren.get(temppren2-1) ;
                    }
              else if(t + 2 < fun.length() && Character.toString(fun.charAt(t+2)).equals("^")){
                  temp2 = exp.get(expcount);
                  int z = t + 4;
                  int tempexpcount = expcount;
                  while(z < fun.length() && Character.toString(fun.charAt(z)).equals("^")){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z + 2;
                  }
              }
              else{
                   temp2 = varablies.get(varcount);
              }
               double tot = temp1/ temp2;
               mul_div.add(tot);
               //System.out.println(temp1 + " " + temp2);
             }

             else if (Character.toString(fun.charAt(t-2)).equals("*") ||Character.toString(fun.charAt(t-2)).equals("/")){
              Double temp1 = mul_div.get(mul_div.size() -1);
              Double temp2 = 0.0;
                          if(Character.toString(fun.charAt(t+1)).equals("(")){
                              int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1);
            }
              else if(t + 2 < fun.length() && Character.toString(fun.charAt(t+2)).equals("^")){
              temp2 = exp.get(expcount);
                                int z = t + 4;
                  int tempexpcount = expcount;
                  while(z < fun.length() && Character.toString(fun.charAt(z)).equals("^")){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z + 2;
                  }
              }
              else{
                   temp2 = varablies.get(varcount);
              }

               double tot = temp1 / temp2;
               mul_div.add(tot);
             }
             else if(Character.toString(fun.charAt(t-2)).equals("^")){
               Double temp1 = exp.get(expcount -1);
               Double temp2 = 0.0;
                          if(Character.toString(fun.charAt(t+1)).equals("(")){
                              int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1);          }
               else if( t + 2 < fun.length() && Character.toString(fun.charAt(t+2)).equals("^")){
               temp2 = exp.get(expcount);
                                int z = t + 4;
                  int tempexpcount = expcount;
                  while(z < fun.length() && Character.toString(fun.charAt(z)).equals("^")){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z + 2;
                  }
              }
              else{
                   temp2 = varablies.get(varcount);
              }
              double tot = temp1 / temp2;
              mul_div.add(tot);
            }
          }
          else if(Character.toString(fun.charAt(t)).equals("^") && pfound == 0){
            expcount++;
          }
          else if(!(Character.toString(fun.charAt(t)).equals("*")) &&!(Character.toString(fun.charAt(t)).equals("/")) &&!(Character.toString(fun.charAt(t)).equals("(")) &&!(Character.toString(fun.charAt(t)).equals(")")) &&!(Character.toString(fun.charAt(t)).equals("+")) && !(Character.toString(fun.charAt(t)).equals("-"))){
            varcount++;
           }
       }
       return mul_div;
 }
         
   public static ArrayList<Double> addition_subtraction(String fun,ArrayList<Double> exp,ArrayList<Double> pren, ArrayList<Double> mul_div, ArrayList<Double> plus_minus, ArrayList<Double> varablies, String var, String var2, String var3){
      int t;
      int prencount = 0;
      int first = 0;
      int varcount  =0;
      int minus_plus_count = 0;
      int timeCount = 0;
      int expcount = 0;
      int pfound = 0;
      int ptotal = 0;
      for(t = 0; t < fun.length(); t++){
         String i = Character.toString(fun.charAt(t));
         if(i.equals("(")){
            pfound = 1;
            ptotal++;
         }
         else if (i.equals(")")){
            prencount++;
            ptotal--;
            if(ptotal == 0){
            pfound = 0;
             }
         }
         if(i.equals("+") && pfound == 0){
            minus_plus_count++;
            if(Character.toString(fun.charAt(t-1)).equals(")")){
               Double temp1 = pren.get(prencount -1);
               Double temp2 = 0.0;
               if(Character.toString(fun.charAt(t+1)).equals("(")){
                                 int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1);                }
                else if(t + 2 < fun.length() &&Character.toString(fun.charAt(t+2)).equals("^")){
                  temp2 = exp.get(expcount);
                  int z = t+4;
                  int tempexpcount = expcount;
                  while(z< fun.length() && (Character.toString(fun.charAt(z)).equals("^"))){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z +2;
                   }
                }
                else if(t + 2 < fun.length() && (Character.toString(fun.charAt(t+2)).equals("*")||Character.toString(fun.charAt(t+2)).equals("/"))){
                  temp2 = exp.get(timeCount);
                  int z = t + 4;
                  int temptime = timeCount;
                  while(z< fun.length() && ((Character.toString(fun.charAt(z)).equals("*"))||(Character.toString(fun.charAt(z)).equals("/")))){
                     temptime++;
                     temp2 = exp.get(temptime);
                     z = z +2;
                   }
                 }
                 else{
                  temp2 = varablies.get(varcount);
                  }
              double tot = temp1 + temp2;
               plus_minus.add(tot);
               }
                 
           else if( t - 2 < 0 ){
            Double temp1 = varablies.get(varcount-1);
            
            Double temp2 = 0.0;
                    if(Character.toString(fun.charAt(t+1)).equals("(")){
                                 int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1);                }
                else if(t + 2 < fun.length() &&Character.toString(fun.charAt(t+2)).equals("^")){
                  temp2 = exp.get(expcount);
                  int z = t+4;
                  int tempexpcount = expcount;
                  while(z< fun.length() && (Character.toString(fun.charAt(z)).equals("^"))){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z +2;
                   }
                }
                else if(t + 2 < fun.length() && (Character.toString(fun.charAt(t+2)).equals("*")||Character.toString(fun.charAt(t+2)).equals("/"))){
                  temp2 = exp.get(timeCount);
                  int z = t + 4;
                  int temptime = timeCount;
                  while(z< fun.length() && ((Character.toString(fun.charAt(z)).equals("*"))||(Character.toString(fun.charAt(z)).equals("/")))){
                     temptime++;
                     temp2 = exp.get(temptime);
                     z = z +2;
                   }
                 }
                 else{
                  temp2 = varablies.get(varcount);
                  }
               double tot = temp1 + temp2;
               plus_minus.add(tot);
             }
            else if (Character.toString(fun.charAt(t-2)).equals("+") ||Character.toString(fun.charAt(t-2)).equals("-")){
              Double temp1 = plus_minus.get(plus_minus.size() -1);
              Double temp2 = 0.0;   
                     if(Character.toString(fun.charAt(t+1)).equals("(")){
                                 int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1);                }
                else if(t + 2 < fun.length() &&Character.toString(fun.charAt(t+2)).equals("^")){
                  temp2 = exp.get(expcount);
                  int z = t+4;
                  int tempexpcount = expcount;
                  while(z< fun.length() && (Character.toString(fun.charAt(z)).equals("^"))){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z +2;
                   }
                }
                else if(t + 2 < fun.length() && Character.toString(fun.charAt(t+2)).equals("*")||Character.toString(fun.charAt(t+2)).equals("/")){
                  temp2 = exp.get(timeCount);
                  int z = t + 4;
                  int temptime = timeCount;
                  while(z< fun.length() && ((Character.toString(fun.charAt(z)).equals("*"))||(Character.toString(fun.charAt(z)).equals("/")))){
                     temptime++;
                     temp2 = exp.get(temptime);
                     z = z +2;
                   }
                 }
                 else{
                  temp2 = varablies.get(varcount);
                  }

               double tot = temp1 + temp2;
               plus_minus.add(tot);
             }
            else if (Character.toString(fun.charAt(t-2)).equals("*") ||Character.toString(fun.charAt(t-2)).equals("/")){
              Double temp1 = mul_div.get(timeCount - 1);
              Double temp2 = 0.0;
                       if(Character.toString(fun.charAt(t+1)).equals("(")){
                                 int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1);                }
                else if(t + 2 < fun.length() &&Character.toString(fun.charAt(t+2)).equals("^")){
                  temp2 = exp.get(expcount);
                  int z = t+4;
                  int tempexpcount = expcount;
                  while(z< fun.length() && (Character.toString(fun.charAt(z)).equals("^"))){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z +2;
                   }
                }
                else if(t + 2 < fun.length() && Character.toString(fun.charAt(t+2)).equals("*")||Character.toString(fun.charAt(t+2)).equals("/")){
                  temp2 = exp.get(timeCount);
                  int z = t + 4;
                  int temptime = timeCount;
                  while(z< fun.length() && ((Character.toString(fun.charAt(z)).equals("*"))||(Character.toString(fun.charAt(z)).equals("/")))){
                     temptime++;
                     temp2 = exp.get(temptime);
                     z = z +2;
                   }
                 }
                 else{
                  temp2 = varablies.get(varcount);
                  }
               double tot = temp1 + temp2;
               plus_minus.add(tot);
             }
             
            else if(Character.toString(fun.charAt(t-2)).equals("^")){
               Double temp1 = 0.0;
               Double temp2 = 0.0;
               if(timeCount < 0){
                  temp1 = mul_div.get(timeCount -1);
               }
               else{
                  temp1 = exp.get(expcount - 1);
                } 
                       if(Character.toString(fun.charAt(t+1)).equals("(")){
                                 int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1);                }
                else if(t + 2 < fun.length() &&Character.toString(fun.charAt(t+2)).equals("^")){
                  temp2 = exp.get(expcount);
                  int z = t+4;
                  int tempexpcount = expcount;
                  while(z< fun.length() && (Character.toString(fun.charAt(z)).equals("^"))){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z +2;
                   }
                }
                else if(t + 2 < fun.length() && (Character.toString(fun.charAt(t+2)).equals("*")||Character.toString(fun.charAt(t+2)).equals("/"))){
                  temp2 = exp.get(timeCount);
                  int z = t + 4;
                  int temptime = timeCount;
                  while(z< fun.length() && ((Character.toString(fun.charAt(z)).equals("*"))||(Character.toString(fun.charAt(z)).equals("/")))){
                     temptime++;
                     temp2 = exp.get(temptime);
                     z = z +2;
                   }
                 }
                 else{
                  temp2 = varablies.get(varcount);
                  }
      
               double tot = temp1 + temp2;
               plus_minus.add(tot);
             }

          }
          
       else if(i.equals("-") && pfound == 0){
            minus_plus_count++;
            if(Character.toString(fun.charAt(t-1)).equals(")")){
               Double temp1 = pren.get(prencount -1);
               Double temp2 = 0.0;
               if(Character.toString(fun.charAt(t+1)).equals("(")){
                                 int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1);                }
                else if(t + 2 < fun.length() &&Character.toString(fun.charAt(t+2)).equals("^")){
                  temp2 = exp.get(expcount);
                  int z = t+4;
                  int tempexpcount = expcount;
                  while(z< fun.length() && (Character.toString(fun.charAt(z)).equals("^"))){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z +2;
                   }
                }
                else if(t + 2 < fun.length() && (Character.toString(fun.charAt(t+2)).equals("*")||Character.toString(fun.charAt(t+2)).equals("/"))){
                  temp2 = exp.get(timeCount);
                  int z = t + 4;
                  int temptime = timeCount;
                  while(z< fun.length() && ((Character.toString(fun.charAt(z)).equals("*"))||(Character.toString(fun.charAt(z)).equals("/")))){
                     temptime++;
                     temp2 = exp.get(temptime);
                     z = z +2;
                   }
                 }
                 else{
                  temp2 = varablies.get(varcount);
                  }
              double tot = temp1 - temp2;
               plus_minus.add(tot);
               }
                 
           else if( t - 2 < 0 ){
            Double temp1 = varablies.get(varcount-1);
            
            Double temp2 = 0.0;
                    if(Character.toString(fun.charAt(t+1)).equals("(")){
                                 int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1);                }
                else if(t + 2 < fun.length() &&Character.toString(fun.charAt(t+2)).equals("^")){
                  temp2 = exp.get(expcount);
                  int z = t+4;
                  int tempexpcount = expcount;
                  while(z< fun.length() && (Character.toString(fun.charAt(z)).equals("^"))){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z +2;
                   }
                }
                else if(t + 2 < fun.length() && (Character.toString(fun.charAt(t+2)).equals("*")||Character.toString(fun.charAt(t+2)).equals("/"))){
                  temp2 = exp.get(timeCount);
                  int z = t + 4;
                  int temptime = timeCount;
                  while(z< fun.length() && ((Character.toString(fun.charAt(z)).equals("*"))||(Character.toString(fun.charAt(z)).equals("/")))){
                     temptime++;
                     temp2 = exp.get(temptime);
                     z = z +2;
                   }
                 }
                 else{
                  temp2 = varablies.get(varcount);
                  }
               double tot = temp1 - temp2;
               plus_minus.add(tot);
             }
            else if (Character.toString(fun.charAt(t-2)).equals("+") ||Character.toString(fun.charAt(t-2)).equals("-")){
              Double temp1 = plus_minus.get(plus_minus.size() -1);
              Double temp2 = 0.0;   
                     if(Character.toString(fun.charAt(t+1)).equals("(")){
                                 int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1) ;               }
                else if(t + 2 < fun.length() &&Character.toString(fun.charAt(t+2)).equals("^")){
                  temp2 = exp.get(expcount);
                  int z = t+4;
                  int tempexpcount = expcount;
                  while(z< fun.length() && (Character.toString(fun.charAt(z)).equals("^"))){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z +2;
                   }
                }
                else if(t + 2 < fun.length() && Character.toString(fun.charAt(t+2)).equals("*")||Character.toString(fun.charAt(t+2)).equals("/")){
                  temp2 = exp.get(timeCount);
                  int z = t + 4;
                  int temptime = timeCount;
                  while(z< fun.length() && ((Character.toString(fun.charAt(z)).equals("*"))||(Character.toString(fun.charAt(z)).equals("/")))){
                     temptime++;
                     temp2 = exp.get(temptime);
                     z = z +2;
                   }
                 }
                 else{
                  temp2 = varablies.get(varcount);
                  }

               double tot = temp1 - temp2;
               plus_minus.add(tot);
             }
            else if (Character.toString(fun.charAt(t-2)).equals("*") ||Character.toString(fun.charAt(t-2)).equals("/")){
              Double temp1 = mul_div.get(timeCount - 1);
              Double temp2 = 0.0;
                       if(Character.toString(fun.charAt(t+1)).equals("(")){
                                 int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1);                }
                else if(t + 2 < fun.length() &&Character.toString(fun.charAt(t+2)).equals("^")){
                  temp2 = exp.get(expcount);
                  int z = t+4;
                  int tempexpcount = expcount;
                  while(z< fun.length() && (Character.toString(fun.charAt(z)).equals("^"))){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z +2;
                   }
                }
                else if(t + 2 < fun.length() && Character.toString(fun.charAt(t+2)).equals("*")||Character.toString(fun.charAt(t+2)).equals("/")){
                  temp2 = exp.get(timeCount);
                  int z = t + 4;
                  int temptime = timeCount;
                  while(z< fun.length() && ((Character.toString(fun.charAt(z)).equals("*"))||(Character.toString(fun.charAt(z)).equals("/")))){
                     temptime++;
                     temp2 = exp.get(temptime);
                     z = z +2;
                   }
                 }
                 else{
                  temp2 = varablies.get(varcount);
                  }
               double tot = temp1 - temp2;
               plus_minus.add(tot);
             }
             
            else if(Character.toString(fun.charAt(t-2)).equals("^")){
               Double temp1 = 0.0;
               Double temp2 = 0.0;
               if(timeCount < 0){
                  temp1 = mul_div.get(timeCount -1);
               }
               else{
                  temp1 = exp.get(expcount - 1);
                } 
                       if(Character.toString(fun.charAt(t+1)).equals("(")){
                                 int temppren = prencount;
               int temppren2 = prencount;
               int z;
               for(z = t +1; z <fun.length(); z++){
                  String II = Character.toString(fun.charAt(z));
                  if(II.equals("(")){
                     temppren++;
                     temppren2++;
                  }
                  else if(II.equals(")")){
                     temppren= temppren -1;
                     if(temppren == 0){
                        z = fun.length() +1;
                      }
                  }
              }
temp2 = pren.get(temppren2-1);                }
                else if(t + 2 < fun.length() &&Character.toString(fun.charAt(t+2)).equals("^")){
                  temp2 = exp.get(expcount);
                  int z = t+4;
                  int tempexpcount = expcount;
                  while(z< fun.length() && (Character.toString(fun.charAt(z)).equals("^"))){
                     tempexpcount++;
                     temp2 = exp.get(tempexpcount);
                     z = z +2;
                   }
                }
                else if(t + 2 < fun.length() && (Character.toString(fun.charAt(t+2)).equals("*")||Character.toString(fun.charAt(t+2)).equals("/"))){
                  temp2 = mul_div.get(timeCount);
                  int z = t + 4;
                  int temptime = timeCount;
                  while(z< fun.length() && ((Character.toString(fun.charAt(z)).equals("*"))||(Character.toString(fun.charAt(z)).equals("/")))){
                     temptime++;
                     temp2 = mul_div.get(temptime);
                     z = z +2;
                   }
                 }
                 else{
                  temp2 = varablies.get(varcount);
                  }
      
               double tot = temp1 - temp2;
               plus_minus.add(tot);
             }

          }
          else if( i.equals("(") || i.equals("+")|| i.equals("-")|| i.equals(")")){
            }
          else if(Character.toString(fun.charAt(t)).equals("*") || Character.toString(fun.charAt(t)).equals("/")){
            timeCount++;
           }
         else if( Character.toString(fun.charAt(t)).equals("^")){
            expcount++;
         }
         else{
            varcount++;
           }
       }
       
       return plus_minus;       
}
      
}
