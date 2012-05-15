function u = algo49(A, k)
%ALGO49(A, k) Computes the generalized k-core of an unweighted undirected graph G 
%  -A is the adjacency matrix representing G
%  -k is the order of the core
% Output : A vector containing the core numbers associated to each node. 

%compute degree measure
function r = computeMeasure(Aff)
	r = Aff*ones(size(Aff,1),1);
end

%count number of ones in a vector
function n=numberOfOnes(vec)
	n = 0;
	for i=1:size(vec)
		if vec(i) == 1
			n = n+1;
		end
	end
end

%init k-core membership vector
u = ones(size(A,1),1);

rho = computeMeasure(A);

while numberOfOnes(u)>1 && min(rho)<k
	[min, argmin] = min(rho);
	
	A = [A(:,1:argmin-1), A(:,argmin+1:end)]; %remove column argmin
	A = [A(1:argmin-1,:); A(argmin+1:end,:)]; %remove row argmin
	
	u(argmin) = 0;
	
	rho = computeMeasure(A);
end


end

