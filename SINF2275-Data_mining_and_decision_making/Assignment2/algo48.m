function u = algo48(A, k)
%ALGO48 Summary of this function goes here
%   Detailed explanation goes here

%compute degree measure
function r = computeMeasure(Aff)
	r = Aff*ones(size(Aff),1);
end

%init k-core membership vector
u = ones(size(A),1);

rho = computeMeasure(A);

while size(rho)>0  && min(rho)<k
	[min, argmin] = min(rho);
	
	A = [A(:,1:argmin-1), A(:,argmin+1:end)]; %remove column argmin
	A = [A(1:argmin-1,:); A(argmin+1:end,:)]; %remove row argmin
	
	u(argmin) = 0;
	
	rho = computeMeasure(A);
end


end

