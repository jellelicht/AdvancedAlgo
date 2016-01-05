function cut = GW(V,W);
%V is already row oriented
n = length(W);
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
cut

    

 
    










