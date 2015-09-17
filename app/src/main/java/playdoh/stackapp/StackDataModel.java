package playdoh.stackapp;

import java.util.ArrayList;

/**
 * Created by fengyali on 9/15/15.
 * This Class is designed as the data model of this application.For short: The M for MVC;
 * It supplies pop, push, and getStackContent method for further use in Android Classes;
 */
public class StackDataModel {

    private ArrayList<Integer> stack_model = new ArrayList<>();

    private int maxIndex() {
//        if(stack_model.isEmpty()){
//            throw new Exception("Attempt to get the index of an empty array.");
//        }
        return stack_model.size() - 1;
    }

    public Integer pop(){
        if(!stack_model.isEmpty()){
            return stack_model.remove(this.maxIndex());
        }else{
            return null;
        }
    }

    public boolean push(Integer number){
        if(stack_model.size() >= 3){
            return false;
        }else{
            stack_model.add(stack_model.size(),number);
            return true;
        }
    }

    public String getStackContent(){
        String list_content = "";
        for(int i=0; i<stack_model.size(); i++){
            if(i == 0){
                list_content += stack_model.get(i).toString();
            }else{
                list_content += " " + stack_model.get(i).toString();
            }
        }
        return "[" + list_content + "]";
    }

    public boolean isStackEmpty(){
        return stack_model.isEmpty();
    }

}
