function best = GW(V,W,T);
%V is already row oriented
n = length(W);
trials = T;
best = 0;
for i=1:trials

    S = [];
    notS = [];
    r = rand(n,1);
    r = r * 1/sum(r);
    for i = 1:n
        if V(i,:)*r > 0
            S = [S i];
        else
            notS = [notS i];
        end
    end

    cut = 0;
    for inS = S
        for ninS = notS
            cut = cut + W(ninS,inS);
        end
    end
    if cut > best
        best = cut;
    end
end



    

 
    










