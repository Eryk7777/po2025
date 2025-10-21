public class CodingBat {
    public static void main(String[] args) {
    }
    public boolean sleepIn(boolean weekday, boolean vacation) {
        if(weekday && vacation){
            return true;
        }
        else if(weekday){
            return false;
        }
        else if(vacation){
            return true;
        }
        else {
            return true;
        }
    }

    public boolean nearHundred(int n) {
        if((n>=90 && n<=110)||(n>=190 && n<=210)){
            return true;
        }
        else{
            return false;
        }
    }

    public int countEvens(int[] nums) {
        int sum=0;
        for(int i=0; i<nums.length; i++){
            if(nums[i]%2==0){
                sum = sum + 1;
            }
        }
        return sum;
    }

    public String helloName(String name) {
        return "Hello " + name + "!";
    }


}