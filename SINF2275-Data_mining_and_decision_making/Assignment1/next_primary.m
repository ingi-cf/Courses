function [path] = next_primary(i,map)
%return the only next state
    path = find(map(i,:) == 1);
endfunction
