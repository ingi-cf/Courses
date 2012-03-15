function  simulate()

repeat = 10;
maps.s0     = 1;
maps.d      = 11;
maps.links  = [ 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0;
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

maps(2).s0      = 1;
maps(2).d       = 11;
maps(2).links   = [ 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0;
                    0 0 1 0 0 0 0 0 0 0 0 0 0 0 0;
                    0 0 0 1 0 0 0 0 0 0 0 2 0 0 0;
                    0 0 0 0 1 0 0 0 0 0 0 0 0 0 0;
                    0 0 0 0 0 1 0 0 0 0 0 0 0 0 0;
                    0 0 0 0 0 0 1 0 0 0 0 0 0 0 0;
                    0 0 0 0 0 0 0 1 0 0 0 0 0 0 0;
                    0 0 0 0 0 0 0 0 1 0 0 0 0 0 0;
                    0 0 0 0 0 0 0 0 0 1 0 0 0 0 0;
                    0 0 0 0 0 0 0 0 0 0 1 0 0 0 0;
                    1 0 0 0 0 0 0 0 0 0 0 0 0 0 0;
                    0 0 0 0 0 0 0 0 0 0 0 0 1 0 0;
                    0 0 0 0 0 0 0 0 0 0 0 0 0 1 0;
                    0 0 0 0 0 0 0 0 0 0 0 0 0 0 1;
                    0 0 0 0 0 0 0 0 0 0 1 0 0 0 0];

traps = zeros(1,15);
traps(4) = 1;
traps(6) = 1;
traps(8) = 1;
maps(1).traps = traps;
maps(2).traps = traps;

for m=1:size(maps,2)
    policy(1,:) = ones(1,15);
    policy(2,:) = ones(1,15)*2;
    policy(3,:) = snake(maps(m));

    policy_stats = zeros(size(policy,1),3);


    for p=1:size(policy,1)
        plays=0;
        for i =1:repeat
            play = simul(maps(m),policy(p,:));
            plays = plays + play;
            if policy_stats(p,1) == 0 || policy_stats(p,1) > play
                policy_stats(p,1) = play;
            end
            if(policy_stats(p,2) < play)
                policy_stats(p,2) = play;
            end
        end
        policy_stats(p,3) = (plays) / repeat;
       % printf('maps:%d policy %d : %d %d %f \n',m,p,policy_stats(p,1), policy_stats(p,2), policy_stats(p,3));
    end
end
