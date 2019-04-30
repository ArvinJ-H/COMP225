 class PNode {
	// Basic node 
		int deg;  // The degree of a term
		float coeff;  // The coefficient of a term
		PNode next;
		
		PNode (int d, float c) {  // Constructor: builds a node with given data
			next= null;
			deg= d;
			coeff= c;
		}
		
		PNode (int d, float c, PNode n){ // Constructor: builds a node with given reference
			next = n;
			deg= d;
			coeff= c;
		}
		
		// Basic node operations
		
		void scale(float k) { // scales coeff by k
		 coeff= coeff*k;
		}
		
		void multiplyByX(int d) { // increases the degree by d
			 deg= deg + d;
			}

		boolean simplified(PNode p) { 
			PNode temp = p;
			if(temp.coeff == 0) {
				return false;
			}
			while (temp.next != null) {
				
				if(temp.coeff == 0) {
					return false;
				}
				if(temp.deg<=temp.next.deg) {
					return false;
				}
				temp = temp.next;
			}
			// PRE: p is the first node of a list of PNodes.
								// POST: Returns true iff the nodes are sorted (descending) according to their degree field	
								// AND all nodes have distinct deg fields (no repeats)
			                    // AND all nodes have a non-zero coefficient field.
			return true;
		}
		
		
		PNode simplify(PNode p) { 
			PNode temp = p;
			PNode temp1 = p.next;
			
			while(temp1.next != null) {
				if(temp.coeff==0) {
					p= temp1;
					temp=p;
					temp1 = temp.next;
					
				}

				if(temp.deg>temp1.deg) {
					temp = temp1;
					temp1 = temp1.next;
				}
				else if(temp.deg == temp1.deg){
					temp.coeff += temp1.coeff;
					temp1 = temp1.next;
					temp.next = temp1;
				}
				
			}
			
			
			
							// PRE: p is the first node of a list of PNodes. All nodes in the list are already
							// Sorted descending according to their deg field
							// POST:  Rearranges the list so that it is simplified but equivalent to the original representation,
			              	// i.e. ensures that each node has distinct deg field (by using polynomial arithmetic)
							// AND removes any nodes with coeff field set to 0.
							// The simplified representation must be mathematically equivalent (as a polynomial)
			              	// to the original representation.
							// Returns the first node of the now rearranged list
			return p;
			
		}
		
		PNode priorityAdd(PNode p, int d, float c) {
			
			PNode first = p;
			PNode temp = p.next;
			
			PNode a = new PNode(d,c);
			
			if(temp.deg< d) {
				a.next = first;
			}
			
			while(temp.next != null) {
				if(temp.deg< d) {
					a.next = temp;
					first.next = a;
				}
				else if(temp.deg == d) {
					first.coeff = first.coeff+c;
				}
				
				
				temp = temp.next;
			}
			
			
			
			
			
			
			//TODO
										   	// PRE: the given list p list is simplified (simplified returns TRUE); 
			                               	// POST: Creates a new PNode with the given data (deg = d, coeff=c) and adds it to the
		                                   	// current list so that the result is also simplified.
											// Returns the first node of the list with the new addition
		  return p;	
		}
}

 class PolyList {
	 PNode first= null;
	 // Class invariant -- an instance of PolyList must always satisfy the condition: 
	 // The list is empty OR 
	 // (The list is not empty AND
	 // All nodes have distinct deg fields AND
	 // There are no nodes having coeff equal to 0 AND
	 // All nodes are ordered from first to last with decreasing deg field. )
	 
	 PolyList(PNode p) { // Constructor that sets its first node to the given PNode p.
		 first= p;
	 }
	 
	 public PNode duplicate(PNode p) {
			if (p == null) {
				return null;
			} 
			else {
				PNode temp = new PNode(p.deg, p.coeff);
				PNode temp2 = temp;
				PNode nNode = p.next;
				while (nNode != null) {
					temp2.next = new PNode(nNode.deg, nNode.coeff);
					nNode = nNode.next;
					temp2 = temp2.next;
				}
				return temp;
			}
		}
	 
	 int getDegree() { 
		 int degree = first.deg;
		 
		 if(first == null) {
			 degree = 0;
		 }
		 
		 PNode temp = first;
		 
		 while(temp.next != null) {
			 temp= temp.next;
			 if(temp.deg > degree) {
				 degree = temp.deg;
			 };
			 return degree;
		 }
		 
		 //TODO
		 				// Returns the largest degree in the list representation.
		 return degree;
	 }
	 
	 float getConstant() { 
		 
		 float con = first.coeff;
		 PNode temp = first;
		 if (first == null) {
			 con = 0;
		 }
		 
		 if (first.deg ==0) {
			 con = first.coeff;
		 }
		 
		 while(temp.next != null) {
			 temp = temp.next;
			 if(temp.deg == 0) {
				 con = temp.coeff;
			 }
			 
		 }
		 
		 
		 //TODO
		 					// Returns the coefficient of the node with zero degree
		 return con;
	 }
	 
	 boolean isConstant() {
		 if (first == null) {
			 return false;
		 }
		 
		 while(first.next !=null) {
			 first = first.next;
			 if(first.deg != 0){
				 return false;
			 }
		 }
		 
		 //TODO
		 					// Returns true iff the polynomial is constant (as a function).
		 return true;
	 }

	 
		// For CR/D performance
	 
	 float evaluate(float a) { 
		 
		 float result = 0;
		 
		 if(first == null) {
			 result =0;
		 }
		 
		 while(first.next!=null) {
			 result =(float) (result + Math.pow((a * first.coeff),first.deg));
			 
			 first = first.next;
		 }
		 //TODO
		 						// returns the result of the polynomial evaluated at a.
		 return result;		 
	 }
	 
	 
	void multiplyByX(int d) {
		
		
		PNode temp = first;
		while (temp != null) {
			temp.multiplyByX(d);
			temp = temp.next;
		}
		first = first.simplify(first);
		first = duplicate(first); // Multiplies the polynomial by x^d (x to the power of d).
		
		
		//TODO
							// Multiplies the polynomial by x^d (x to the power of d).
	}
	
	void add(PolyList p) { 
		
		PNode temp = p.first;
		while (temp != null) {
			first = first.priorityAdd(first, temp.deg, temp.coeff);
			temp = temp.next;
		}
		first = first.simplify(first);
		first = duplicate(first); // Adds the given polynomial p to the current polynomial.
	}
	
	void differentiate() {
		PNode temp = first;
		while (temp != null) {
			if (temp.deg != 0) {
				temp.coeff = temp.coeff * temp.deg;
				--temp.deg;
				if (temp.next == null || temp.next.deg == 0) {
					temp.next = null;
					break;
				}
			}
			temp = temp.next;
		}
		first = first.simplify(first); // Differentiates the current polynomial.
	}
	
	// For HD performance
	
	PolyList pGCD(PolyList p, PolyList q) { //TODO
		                                    // PRE: p, q are polynomials with leading term having coeff field equal to 1 
											// Returns the GCD of p and q. 
		                                    // Ensure that the coeff field of the leading term is 1. 
		return q;
	}		
}
 
