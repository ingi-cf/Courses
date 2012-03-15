function [paths] = next_paths(i,m)
%return a line matrix with all paths
    paths = find(m.links(i,:) > 0);
endfunction
