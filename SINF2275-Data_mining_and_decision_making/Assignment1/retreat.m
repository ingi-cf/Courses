function [s] = retreat(i,m)
    s = back(back(i,m),m);
endfunction

function [s] = back(i,m)
    if(i ~= m.s0)
        maxMap = max(max(m.links));
        for j=1:maxMap
            f = find(m.links(:,i) == j);
            if size(f,1) >0
                s = f(1);
                break
            end
        end
    else
        s = m.s0;
    end
endfunction
