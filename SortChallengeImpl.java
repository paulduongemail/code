import java.util.Scanner;

public class SortChallengeImpl implements SortChallenge{

	@Override
	public int[] simpleSort(int[] list, boolean ascending) {
	
		if (list.length == 0) return list;
	    int len = list.length;
	    for (int i = len / 2-1; i >= 0; i--)
        {
	    	list = sort(list, len, i);
        }

	    for (int i = len-1; i >= 0; i--) {
	        int temp = list[0];
	        list[0] = list[i];
	        list[i] = temp;
	        list = sort(list, i, 0);
	    }
		
	    if(ascending) {
	    	return list;
	    } else {
	    	int desc[] = new int[len];
	    	for( int i = len-1; i >= 0; i--) {
	    		desc[len-1 - i] = list[i]; 
	    	}
	    	return desc;
	    }
	}
	// heap sort
	static int[] sort(int[] list, int len, int i) {
	    int left = (2*i)+1;
	    int right = (2*i)+2;
	    int max = i;

	    if (left < len && list[left] > list[max]) {
	        max = left;
	    }

	    if (right < len && list[right] > list[max]) {
	        max = right;
	    }

	    if (max != i) {
	        int temp = list[i];
	        list[i] = list[max];
	        list[max] = temp;
	        list = sort(list, len, max);
	    }
	    
	    return list;
	}

	@Override
	public void printSortedFrequency(int[] list) {
		list = simpleSort(list, true);
		int count = 1;
		for(int i = 0; i < list.length; i++) {

			while(i+1 <= list.length-1 && list[i] == list[i+1]) {
				count++;
				i++;
			}
			System.out.println(list[i] + " appears " + count+ (count>1 ? " times" : " time"));
			count = 1;
		}
	}

	@Override
	public int numberOfUniqueValues(int[] list) {
		list = simpleSort(list, true);
		int count = 0;
		for(int i = 0; i < list.length; i++) {
			while(i < list.length-1 && list[i] == list[i+1]) {
				i++;
			}
			count++;
		}
		return count;
	}

	public static void main(String args[]) {
//		int list[] = { 24, 12, 8, 12, 19, 12, 19, 19, 19, 24};
		System.out.println("Enter the size of array : ");
		Scanner scanner = new Scanner(System.in);
		
		int len = scanner.nextInt();
		int list [] = new int [len];
		
		System.out.println("Enter the elements:");
		
		for( int i = 0; i < len; i++)
			list[i] = scanner.nextInt();
		
		System.out.println("Enter sorting order: 0 for ascending and 1 for descding: ");
		int sort = scanner.nextInt();
		
		SortChallenge sc = new SortChallengeImpl();
		
		sc.printSortedFrequency(list);
		
		System.out.println("Number of unique values : "+sc.numberOfUniqueValues(list));

		list = sc.simpleSort(list, sort <= 0 ? true : false);
		System.out.println("Sorted Array in " + (sort <= 0 ? "Ascending order:" : "Descending order: "));
		
		for( int i = 0; i < list.length; i++)
			System.out.print(list[i] + " ");
	}
}
