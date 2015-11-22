

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Sorting extends JFrame {
    
    // Reference to the array to be drawn
    int[] array = { 1, 100, 150, 40, 120, 255, 230, 10, 50 };
    int width = 600;
    
    public static void main(String[] args) {
        new Sorting();
    }
    
    public Sorting() {
        super("sorting");
        setSize(width, width);
        setVisible(true);
        
        int[] numbers = randomize(10000);
        visualize(numbers);
        //bubbleSort(numbers);
        //selectionSort(numbers);
        quicksort(numbers);
    }
    
    private int[] randomize(int size) {
        // Create an array of that size
        int[] a = new int[size];
        // Go to each position in the array
        for (int i = 0 ; i < a.length ; i++) {
            a[i] = (int) (Math.random() * 256);
        }
        return a;
    }
    private void selectionSort( int[] list){ 
      for(int j = 0 ; j < list.length ; j++) {
    	int min = list[j],
    		minIndex = j;
    	for (int i = j + 1; i < list.length ; i++) { 
    		if(list[i] < min ) { 
    			min = list[i];
    			minIndex = i;
    		}
    	}
    	int save = list[j]; 
    	list[j] = list[minIndex];
    	list[minIndex] = save;
    	visualize(list, 10);
      }
    }
    	
    private void insertionSort(int [] list) { 
    }
    private void quicksort (int[] list, int start, int end) { 
    	if (start < end ) { 
    		int pivot = list[end]; 
    		int i = start;
    		for(int j = start ; j< end - 1; j++) { 
    			if(list[j] < pivot) { 
    				int save = list[i]; 
    				list[i] = list[j];
    				list[j] = save; 
    				i = i+1; 
    			}
    		}
    		int save = list[end]; 
    		list[end] = list[i]; 
    		list[i] = save;
    		visualize(list, Math.min(end - start, 1000));
    		quicksort(list, start, i-1); 
    		quicksort(list, i+1, end);
    		
    	}
    	
    }
    	public void quicksort(int[] list) { 
    		quicksort(list, 0, list.length - 1);
    	}
   

    private void bubbleSort(int[] list) {
        
        for (int j = 0 ; j < list.length ; j++) {
            // Go to every index in the list except the last one
            for (int i = 0 ; i < list.length - 1 ; i++) {
                if (list[i] > list[i + 1]) {
                    int save = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = save;
                    
                    
                }
            }
            visualize(list, 1);
        }
        
    }
    
    // override the visualize method to draw the array with no delay
    public void visualize(int[] a) {
        visualize(a, 0);
    }

    // Visualizing the array means saving a reference to it,
    // then repainting the window.
    public void visualize(int[] a, int delay) {
        array = a;
        repaint();
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {}
    }
    
    public void paint(Graphics g) {
        int size = (int) Math.ceil(Math.sqrt(array.length));
        int scale = width / size;
        // Find the max
        int max = 255;
        
        // Go to each index in the array
        for (int i = 0 ; i < array.length ; i++ ) {
            int x = (i % size) * scale;
            int y = i / size * scale;
            
            g.setColor(Color.getHSBColor(0.4f, 0.5f, array[i] / 255f));
            g.fillRect(x, y, scale, scale);
        }
    }
}
