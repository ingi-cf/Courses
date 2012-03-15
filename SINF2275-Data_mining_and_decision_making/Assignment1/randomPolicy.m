function ran = randomPolicy(List)
%RANDOMPOLICY Summary of this function goes here
%   Detailed explanation goes here

ran = 2.*rand(1,length(List));

for(i=1:length(ran))
    if ran(i)>1
        ran(i)=1;
    else
        ran(i)=2;
    end
end
end

