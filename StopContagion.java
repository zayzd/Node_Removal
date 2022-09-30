import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class StopContagion{
	//create an arraylist called graph
	static ArrayList<ArrayList<Integer>> graph;
	static int V;
	//takes in ndes
	StopContagion(int nodes){
		V = nodes;
		graph = new ArrayList<ArrayList<Integer>>();
		for(int i = 1;i<=V;i++){
			graph.add(new ArrayList<Integer>());
		}

	}
	
	 
	//method to print adjacency list of the graph
	public static void printAdjacencyList(ArrayList<ArrayList<Integer> > adj,int vertices) {
		//for loop to run vertices times
		for (int i = 0; i < vertices; i++) {
			//add 1 to source for compatibility
			int src = i+1;
			//print source
        	System.out.print(src+"-->");
	        for (int j = 0; j < adj.get(i).size(); j++) {
	        	//add neighbors of source to an integer
	        	int neighbors = adj.get(i).get(j)+1;
	        	//print out the neighbors
	            System.out.print(neighbors+" ");
	           }
	         System.out.println();
	        }
	    }
	
	
	
	
	//count connections by returning size 
	public static void connections(ArrayList<ArrayList<Integer> > adj) {
		for (int i = 0; i < 30; i++) {
			int src = i+1;
			
	        for (int j = 0; j < adj.get(i).size(); j++) {
	           }
	        //print the source and the number of its connections
            System.out.println("Number of connections for vertex " +src+" is "+adj.get(i).size());
            System.out.println();


	        }

	    }
	
	public static int connections(ArrayList<ArrayList<Integer> > adj,int vertices) {
		int vertex1 = 0;
		//loop for the number of vertices
		for (int i = 0; i < vertices; i++) {
			int src = i+1;
			//grap the size of its adjacency list, which will return the number of its connections
//	        


	        }
		//return the number of connections
			int connections = adj.get(vertices).size();
        	return connections;


	    }
	  

	private static void addEdge(ArrayList<ArrayList<Integer>> al, int i, int j) {
		//add an undirected edge between i and j
        al.get(i).add(j);
        al.get(j).add(i);
    }
	
	public static void deleteEdge(ArrayList<ArrayList<Integer>>al, int u, int v) {
		//search through u and delete the edge v
		for(int i = 0; i<al.get(u).size();i++) {
			if(al.get(u).get(i)==v) {
				al.get(u).remove(i);
			}
		}
		//search through v and delete the edge u

		for(int i = 0; i<al.get(v).size();i++) {
			if(al.get(v).get(i)==u) {
				al.get(v).remove(i);

			}
		}
	}
 
    private static int printShortestDistance(ArrayList<ArrayList<Integer>> adj,int s, int dest, int v){
        int pred[] = new int[v];
        int dist[] = new int[v];
 
        if (BFS(adj, s, dest, v, pred, dist) == false) {
//            System.out.println("Given source and destination" +
//                                         "are not connected");
            return 0;
        }
 
        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<Integer>();
        int c = dest;
        path.add(c);
        while (pred[c] != -1) {
            path.add(pred[c]);
            c = pred[c];
           
        }
        
        int pathlength = dist[dest];
        
        return pathlength;
        
    }
    
    static int dist[];
	private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,int dest, int v, int pred[], int dist[]){
		//create queue
		LinkedList<Integer> queue = new LinkedList<Integer>();
		//keep track if a vertex is visited or not
		boolean visited[] = new boolean[v];
		//update accordingly
		for (int i = 0; i < v; i++) {
			visited[i] = false;
			dist[i] = Integer.MAX_VALUE;
			pred[i] = -1;
		}
		visited[src] = true;
		dist[src] = 0;
		queue.add(src);
		//algorithm for breadth first search

		while (!queue.isEmpty()) {
			int qr = queue.remove();
			for (int i = 0; i < adj.get(qr).size(); i++) {
				if (visited[adj.get(qr).get(i)] == false) {
					visited[adj.get(qr).get(i)] = true;
					dist[adj.get(qr).get(i)] = dist[qr] + 1;
					pred[adj.get(qr).get(i)] = qr;
					queue.add(adj.get(qr).get(i));

					if (adj.get(qr).get(i) == dest)
						return true;
				}
			}
		}
		return false;
	}
	

//standard method to print an array
	public static void printArr(int[]a) {
		for(int i = 0; i<a.length;i++) {
			System.out.print(a[i]+" ");
		}
	}

	//standard method to print a list

	public static void printList(ArrayList<Integer> al){
	     
	     for(int i = 0;i<al.size();i++){
				System.out.print(al.get(i)+" ");
	    	 
	    	 }
		   	System.out.println();

	 }
	
//makes target vertices a static array to allow for its retrieval in the main method
	static ArrayList<Integer> targetvertices;

	public static int findInfluence(ArrayList<ArrayList<Integer>>al, int source, int dest, int vertices, int radius) {
		//create arrayList
		targetvertices = new ArrayList();
		
		   //creates shortest path calculations for all other vertices in the graph
        for(int i = 0; i<vertices;i++) {
        		dest  =i;
        		int source1 = source-1;
        		int dest1 = dest+1;
        		//if the shortest path from source is the same as the radius
                if(printShortestDistance(al,source1,dest,vertices)==radius) {
                	//add to the array list
                	targetvertices.add(dest1);
                }
                 
                
        	}
    	 int sum = 0;
    	 int collectiveInfluence = 0;
    	 int sourceConn = 0;


    	 //calculate collective influence
    	 //loop for the amount of neighbors a vertex has
 	     for(int i = 0;i<targetvertices.size();i++){
 	    	 int ver = targetvertices.get(i);
 	    	 //add the connections of k_i minus 1
 	    	 sum += connections(al,targetvertices.get(i)-1)-1;
 	    	 //make sourceConn the connections of the source -1
 	    	 sourceConn = connections(al,source-1)-1;
 	    	 
 	    	 }
 	     	//calculate collective influence
	    	 collectiveInfluence = sum*sourceConn;

	    	 //return the collectiveInfluence
 	     return collectiveInfluence;
	}
	
	//method for debugging to return the neighbors of a vertex
	public static ArrayList<Integer> returnNeighbors(ArrayList<ArrayList<Integer>>al, int source, int vertices, int radius) {
		targetvertices = new ArrayList();
		
		   //creates shortest path calculations for all other vertices in the graph
        for(int i = 0; i<vertices;i++) {
        		int dest  =i;
        		int source1 = source-1;
        		int dest1 = dest+1;
//                System.out.println("Path length from "+source+" to "+dest1+" : "+printShortestDistance(adj, source1, dest, vertices));
        		
                if(printShortestDistance(al,source1,dest,vertices)==1) {
                	targetvertices.add(dest1);
                }
                 
                
        	}
        return targetvertices;

	}	

	
	//return the largest element in an arraylist
	 public static int largest(ArrayList<Integer>al) {
		 int max = al.get(0);
	        for (int i = 1; i < al.size(); i++) {
	            if (max < al.get(i))
	                max = al.get(i);
	        }
	        return max;
     }
	
	 //traverse an arraylist to find the index of an element
	 public static int findIndex(ArrayList<Integer>al, int t) {
	        int len = al.size();
	        int i = 0;
	 //while the index is less than the size
	        while (i < len) {
	 //if the value at i is equal to t
	        	//return index (+1 for readability)
	            if (al.get(i) == t) {
	                return i+1;
	            }
	            else {
	                i = i + 1;
	            }
	        }
	        //return -1 if not found
	        return -1;
	    }
	 
	 

	public static int largest(int[]arr){
        int i;
         
        // Initialize maximum element
        int max = arr[0];
      
        // Traverse array elements from second and
        // compare every element with current max 
        for (i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];
      //return max plus one
        return max+1;
    }
	
	static int countDistinct(int arr[]){
	    int distinct = 1;
	 
	    // Pick all elements one by one
	    for (int i = 1; i < arr.length; i++)
	    {
	        int j = 0;
	        for (j = 0; j < i; j++)
	            if (arr[i] == arr[j])
	                break;
	 
	        // If not printed earlier,
	        // then print it
	        if (i == j)
	        	distinct++;
	    }
	    return distinct;
	}
	public static void main(String args[]) throws IOException {

		//instantiate numerous variables and scanners
		Scanner s = new Scanner(System.in);
        String ui = args[0];
    
        if(ui.equals("-d")) {
        	int vertices = 0;
        	File file = new File(args[2]);
            Scanner in = new Scanner(file);
            String[]arr;
            int[]input = null;
            //create an array list to store adjacencies
            ArrayList<ArrayList<Integer>> adj =new ArrayList<ArrayList<Integer>>();
            
            //use buffered reader to take all numbers from the file into an array
            byte[] intarr = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);

            fis.read(intarr);
            fis.close();
            String[] valueStr = new String(intarr).trim().split("\\s+");
            //convert to integer
            int[] nums = new int[valueStr.length];
            for (int i = 0; i < valueStr.length; i++) 
            	nums[i] = Integer.parseInt(valueStr[i]);
            //count all of the distinct numbers and assign the result to vertices
             vertices = (countDistinct(nums));
            while(in.hasNextLine()) {
            	//split the line into two numbers, a and b
            	arr = in.nextLine().split(" ");

            }
            
           
            //make the arraylist the size of vertices
            adj =new ArrayList<ArrayList<Integer>>(vertices);
            //graph g has vertices many vertices
            StopContagion g = new StopContagion(vertices);
            Scanner sc = new Scanner(file);
            
            while(sc.hasNext()) {
            	//split input from file
            	arr = sc.nextLine().split(" ");
            	//add vertices many adjacency lists to the array list
            	for(int i = 0;i<vertices;i++) {
            		adj.add(new ArrayList<Integer>());
            	}
            	//a and b are the first 2 numbers in file
            	int a = Integer.parseInt(arr[0])-1;
            	int b = Integer.parseInt(arr[1])-1;
            	//add edge between them
                addEdge(adj,a, b);

            }

        	//times is the number of times we remove or innoculate
            String time = (args[1]);
            
            int times = Integer.parseInt(time);

        int source = 0;
        	//array list of the connections of each node
    		ArrayList<Integer> connections = new ArrayList<Integer>();
    		int max = 0;

    		for(int t = 0; t<times;t++) {
            for(int i = 0;i<vertices;i++) {
            	connections(adj,i);
            	int display = i+1;
            	//loop through each vertex and set the ith value to the  connections of ith vertex in the array list
            	connections.add(0);
                connections.set(i,connections(adj,i));
                //find the largest connection
        		 max = largest(connections);
            	
            }
            //the first index with the degree of max will be largestDegree
            int largestDegree = findIndex(connections,max);
            //print the vertex to be removed and how many connections it has
    		System.out.println(findIndex(connections,max)+" "+largest(connections));
    		ArrayList<Integer>neighbors = returnNeighbors(adj,findIndex(connections,max),vertices,1);
    		//removes the edges between largestDegree and all of its neighbors
    		
    		for(int k = 0;k<neighbors.size();k++) {
    			deleteEdge(adj,largestDegree-1,neighbors.get(k)-1);
    			deleteEdge(adj,neighbors.get(k)-1,largestDegree);
    
    			connections.set(largestDegree-1, 0);
    		}
    		}

        }
        if(ui.equals("-r")) {
        	int vertices = 0;
        	File file = new File(args[3]);
            Scanner in = new Scanner(file);
            String[]arr;
            int[]input = null;
            //create an array list to store adjacencies
            ArrayList<ArrayList<Integer>> adj =new ArrayList<ArrayList<Integer>>();
            
            //use buffered reader to take all numbers from the file into an array
            byte[] intarr = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);

            fis.read(intarr);
            fis.close();
            String[] valueStr = new String(intarr).trim().split("\\s+");
            //convert to integer
            int[] nums = new int[valueStr.length];
            for (int i = 0; i < valueStr.length; i++) 
            	nums[i] = Integer.parseInt(valueStr[i]);
            //count all of the distinct numbers and assign the result to vertices
             vertices = (countDistinct(nums));
            while(in.hasNextLine()) {
            	//split the line into two numbers, a and b
            	arr = in.nextLine().split(" ");

            }
            
           
            //make the arraylist the size of vertices
            adj =new ArrayList<ArrayList<Integer>>(vertices);
            //graph g has vertices many vertices
            StopContagion g = new StopContagion(vertices);
            Scanner sc = new Scanner(file);
            
            while(sc.hasNext()) {
            	//split input from file
            	arr = sc.nextLine().split(" ");
            	//add vertices many adjacency lists to the array list
            	for(int i = 0;i<vertices;i++) {
            		adj.add(new ArrayList<Integer>());
            	}
            	//a and b are the first 2 numbers in file
            	int a = Integer.parseInt(arr[0])-1;
            	int b = Integer.parseInt(arr[1])-1;
            	//add edge between them
                addEdge(adj,a, b);

            }
        	//radius will be the 2nd argument
        	String radiu = args[1];
        	int radius = Integer.parseInt(radiu);
        	
        	String time = args[2];
        	int times = Integer.parseInt(time);

        	int source = 0;
        	int collectiveInfluence = 0;
        	//create an arraylist to store the collective influences
        	ArrayList<Integer> influences = new ArrayList<Integer>();
		
        	//looping for the number of times we want to innoculate
        	for(int i = 0; i<times;i++) {

        		//find the influence of each node
        		for(int j = 0;j<vertices;j++) {
        			source = j+1;
        			collectiveInfluence = findInfluence(adj,source,j,vertices,radius);
        			//add 0 to fill the array list
        			influences.add(0);
        			//use .set instead of .add to allow for continuous updates to the arraylist
        			//as we need to calculate again when vertices are removed
        			influences.set(j,collectiveInfluence);
    			
    		}
        		
        	//find the largest of the influences, and the index with the largest influence	
    		System.out.println();
    		int max = largest(influences);
    		int largestCollective = findIndex(influences,max);
    		

    		ArrayList<Integer>neighbors = returnNeighbors(adj,largestCollective,vertices,1);
    		System.out.println(findIndex(influences,max)+" "+largest(influences));

//    		printList(neighbors);
    		
    		for(int k = 0;k<neighbors.size();k++) {
    			deleteEdge(adj,largestCollective-1,neighbors.get(k)-1);

    			influences.set(largestCollective-1, 0);
//        		printList(influences);
    		}
    		}
    		 
        }
        
      }
}
        
    

