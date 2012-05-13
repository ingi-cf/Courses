function U = algo34(A, K, m, Y, lambda, mu)
%ALGO33 Summary of this function goes here
%   Detailed explanation goes here


%gamma
di = zeros(1,m);
for i=1:m
	di = di + Y(i)
end
gamma = diag(di);

%init U
U = zeros(n,m);

%generalized outdegree matrix ? 
D = diag(A);

%laplacian matrix
L = D-A;

%compute predicted scores for each class
yhat = zeros(1,m)
for c=1:m
	gammak = gamma*K;
	lambdalk = lambda*L*K;
	mumatrix = mu*diag(ones(1, size(gammak)));
	gammay = gamma*Y(c);
	
	betahat = (gammak+lambdalk+mumatrix)\(gammay);
	
	yhat(c) = X*betahat;
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

