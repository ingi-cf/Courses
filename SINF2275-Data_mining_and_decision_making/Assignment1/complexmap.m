function map = complexmap()
%COMPLEXMAP Summary of this function goes here
%   Detailed explanation goes here

map.s0      = 1;
map.d       = 30;
map.links   = zeros(30)
for i=1:length(map.links)
    map.links(i,i+1) = 1;
end

map.links(4,16) = 2;
map.links(14,6) = 2;
map.links(18,2) = 2;
map.links(15,27) = 2;
map.links(25,17) = 2;
map.links(29,13) = 2;

end

