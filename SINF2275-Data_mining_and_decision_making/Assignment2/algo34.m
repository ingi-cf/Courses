function U = algo34(A, K, m, Y, lambda, mu)
%ALGO34(A, K, m, Y, lambda, mu) Performs a laplacian regularized least square for labeling the 
%nodes of a weighted undirected graph G and integrating features availables on the nodes.
% - A is the adjacency matric representing G
% - K is the similarity matrix containing the similarities between nodes
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

%generalized outdegree matrix ? 
D = diag(A*ones(size(A,1),1));

%laplacian matrix
L = D-A;

%compute predicted scores for each class
yhat = zeros(n,m);
for c=1:m
	gammak = gamma*K;
	lambdalk = lambda*L*K;
	mumatrix = mu*diag(ones(1, size(gammak,1)));
	gammay = gamma*Y(:,c);
	
	betahat = (gammak+lambdalk+mumatrix)\(gammay);

	yhat(:,c) = K*betahat;
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

