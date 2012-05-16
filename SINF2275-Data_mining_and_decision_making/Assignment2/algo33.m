function U = algo33(A, X, m, Y, lambda, mu)
%ALGO33(A, X, m, Y, lambda, mu) Performs a laplacian regularized least square for labeling the 
%nodesof a weighted undirected graph G and integrating features availables on the nodes.
% - A is the adjacency matric representing G
% - X contains the features vectors as rows
% - m is the number of classes
% - Y a mxn matrix containing the m binary indicator vectors y(c) representing the classes as rows
% - lambda and mu are regularization parameters 
% Output : the membership matrix, each column representing a class.

n = length(A);
%gamma
di = Y*ones(size(Y,2),1);
gamma = diag(di);

%init U
U = zeros(n,m);

%add bias to X
X = [X, ones(n,1)];

%generalized outdegree matrix ? 
D = diag(A*ones(size(A,1),1));

%laplacian matrix
L = D-A;

%compute predicted scores for each class
yhat = zeros(n,m);
for c=1:m
	xtgammax = X'*gamma*X;
	lambdaxtlx = lambda*X'*L*X;
	mumatrix = mu*diag(ones(1, size(xtgammax,1)));
	xtgammay = X'*gamma*Y(:,c);
	
	w = (xtgammax+lambdaxtlx+mumatrix)\(xtgammay);
	
	yhat(:,c) = X*w;
end

%get argmax of lhat for each node 
lhat=zeros(1,n);
for node=1:n
	max = -Inf;
	for arg=1:m
		val = yhat(node, arg);
		if val>max
			max = val;
			lhat(node) = arg;
		end
	end
end

%compute elements of U
for i=1:n
	U(i, lhat(i)) = 1;
end

end

