function [paths] = next_paths(i,map)
%return a line matrix with all paths
    paths = find(map(i,:) > 0);
endfunction
