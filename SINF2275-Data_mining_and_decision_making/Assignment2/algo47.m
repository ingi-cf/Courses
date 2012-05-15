function core = algo47(A)
%ALGO47(A) Computes the core numbers of an unweighted undirected graph G 
%  -A is the adjacency matrix representing G
% Output : A vector containing the core numbers associated to each node. 

n = length(A);

%degree vector (to verify?)
d = A*ones(n,1);
d = sort(d);

%init core
core = d;

for i=1:n-1
	for j=1:n
		if A(i,j)>0 %j in neighbors of i
			if j>i
				d(j) = d(j)-1;
				if d(i)<d(j)
					core(j) = core(j)-1;
				end
			end
			d = [d(1:i); sort(d(i+1:end))];
		end
	end
end

end

