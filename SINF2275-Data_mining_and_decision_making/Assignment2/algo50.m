function [dendo dendoinert] = algo50(K)
%ALGO50(K) Computes a kernel ward hierarchical clustering of the nodes of a graph G
%   -K is the similarity matrix associated with G
% Output : A matrix representing the dendogram of the hierarchical clustering, each 
% column represents a cluster and contains n elements e_i with 0 as value if the node i is not in the cluster and 
% e_i>0 if it is in the cluster. 
% A secondary output dendoinert gives for each cluster created the within-cluster inertia of the merge. 

[nr,nc] = size(K);

%initialization
h = zeros(nr); %then h_k = h(:,k)
for k=1:nr
	h(k, k) = 1; %h_k = e_k 
end
dendo = h;
n = ones(nr,1);% n(k) = 1 forall k ??
dendoinert = zeros(nr,1);

m = nr;

%init similarities between clusters
deltaj = zeros(m);
for k=1:m
	for l=1:m
		if l!=k
			frac = (n(k)*n(l))/(n(k)+n(l));
			hdiff= h(:,k) - h(:,l);
			deltaj(k,l) = frac*hdiff'*K*hdiff;
		end
	end
end

do
	%find two closest clusters lmin and kmin
	deltaj = deltaj + Inf*eye(size(deltaj)); %lmin != kmin
	[vecmin veclmin] = min(deltaj);
	[scalmin kmin] = min(vecmin);
	lmin = veclmin(kmin);
	
	%new cluster to indexes from k and l
	kul = length(n)+1;
	n(kul) = n(kmin)+n(lmin);
	
	h(:, kul) = (n(kmin)*h(:,kmin)+n(lmin)*h(:,lmin))/(n(kmin)+n(lmin));
	
	%store the factt that clusters have been merged with scalmin as within-cluster inertia in dendogram struct ? 
	dendo = [dendo, h(:, kul)];
	dendoinert(length(dendoinert)+1) = scalmin;
	
	%delete individual clusters kmin and lmin  ??? 
	first = min(kmin, lmin);
	second = max(kmin,lmin)-1;
	n = [n(1:first-1);n(first+1:end)];
	h = [h(:,1:first-1),h(:,first+1:end)];
	n = [n(1:second-1);n(second+1:end)];
	h = [h(:,1:second-1),h(:,second+1:end)];
	kul = length(n);
	m = m-1;
	
	
	%update dissimilarities : 
	deltaj = zeros(m);
	for k=1:m
		if k!=kul
			frac = (n(k)*n(kul))/(n(k)+n(kul));
			hdiff= h(:,k) - h(:,kul);
			deltaj(k,kul) = frac*hdiff'*K*hdiff;
		end
	end
	
	
until m==1

end

