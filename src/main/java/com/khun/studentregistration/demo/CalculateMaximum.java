package com.khun.studentregistration.demo;

public class CalculateMaximum {
    public static int findMax(int arr[]){
        int max=arr[0];
        for(int i=0;i<arr.length;i++){
            if(max<arr[i])
                max=arr[i];
        }
        return max;
    }

        public static boolean m(int p) {
            if (p < 0) {
                p= 1;
                return p > 1;
            }
            return p == 1 || p == 9;
        }
        public static boolean mm() {
            return false;
        }

}
