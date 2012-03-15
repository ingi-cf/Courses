function [path] = next_primary(i,m)
%return the only next state
    path = find(m.links(i,:) == 1);
end
