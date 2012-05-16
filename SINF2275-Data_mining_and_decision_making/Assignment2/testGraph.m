function [A, K, X, Y, m, lambda, mu] = testGraph()
%testGraph Summary of this function goes here
%   Detailed explanation goes here

n = 5;
A = [1 1 1 0 0;
	 1 1 0 1 0;
	 1 0 1 1 0;
	 0 1 1 1 1;
	 0 0 0 1 1;];
	 
 m = 3;
 
 K = A;
 K = K + eye(size(K));
 K(1,2) = 2;
 K(2,1) = 2;
 K(3,4) = 2;
 K(4,3) = 2;
 
 X = zeros(n,1);
 
 Y = zeros(n,m);
 %1 and 2 in class 1
 Y(1,1) = 1;
 Y(2,1) = 1;
 
 %3 and 4 in class 2
 Y(3,2) = 1;
 Y(4,2) = 1;
 
 %5 in class 3
Y(5,3) = 1;

lambda = 1;
mu = 1;

%algo33(A, X, m, Y, lambda, mu)
%algo34(A, K, m, Y, lambda, mu)
%algo47(A)
%algo48(A,2)
%algo49(A,2)
algo50(K)

end

