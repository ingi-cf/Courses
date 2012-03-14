function simulate()

repeat = 1000;
map = [ 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0;
        0 0 1 0 0 0 0 0 0 0 0 0 0 0 0;
        0 0 0 1 0 0 0 0 0 0 0 2 0 0 0;
        0 0 0 0 1 0 0 0 0 0 0 0 0 0 0;
        0 0 0 0 0 1 0 0 0 0 0 0 0 0 0;
        0 0 0 0 0 0 1 0 0 0 0 0 0 0 0;
        0 0 0 0 0 0 0 1 0 0 0 0 0 0 0;
        0 0 0 0 0 0 0 0 1 0 0 0 0 0 0;
        0 0 0 0 0 0 0 0 0 1 0 0 0 0 0;
        0 0 0 0 0 0 0 0 0 0 1 0 0 0 0;
        0 0 0 0 0 0 0 0 0 0 1 0 0 0 0;
        0 0 0 0 0 0 0 0 0 0 0 0 1 0 0;
        0 0 0 0 0 0 0 0 0 0 0 0 0 1 0;
        0 0 0 0 0 0 0 0 0 0 0 0 0 0 1;
        0 0 0 0 0 0 0 0 0 0 1 0 0 0 0];

traps = zeros(1,15);
traps(4) = 1;
traps(6) = 1;
traps(8) = 1;

policy(1,:) = ones(1,15);
policy(2,:) = ones(1,15)*2;

for p=1:size(policy,1)
    play=0;
    for i =1:repeat
        play += simul(map,traps,policy(p,:));
    end
    s_mean = (play +0.0) / repeat;
    printf('policy %i : %f\n',p,s_mean)
end
