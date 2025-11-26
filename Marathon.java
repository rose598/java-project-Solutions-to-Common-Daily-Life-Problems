 class Marathon {
     public static void main(String[] args) {
         String[] names = {"Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt", "Alex",
                 "Emma", "John", "James", "Jane", "Emily", "Daniel", "Neda",
                 "Aaron", "Kate"};

         int[] times = {341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393, 299,
                 343, 317, 265};

         // 找出第一名
         int minTime = times[0];
         String winnerName = names[0];

         for (int i = 1; i < times.length; i++) {
             if (times[i] < minTime) {
                 minTime = times[i];
                 winnerName = names[i];
             }
         }

         // 找出第二名
         int secondMinTime = Integer.MAX_VALUE;
         String secondName = "";

         for (int i = 0; i < times.length; i++) {
             if (times[i] < secondMinTime && times[i] > minTime) {
                 secondMinTime = times[i];
                 secondName = names[i];
             }
         }

         System.out.println("Winner: " + winnerName + ": " + minTime);
         System.out.println("Second: " + secondName + ": " + secondMinTime);
     }
 }
