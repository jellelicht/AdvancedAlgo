function [LB,UB, elapsed] = readSdp(s);
%read graph into laplacian matrix L
%s = 'test2.txt';
fid = fopen(s);
tline = fgets(fid);
n = str2num(tline);
W = zeros(n,n);
while ischar(tline)
    tline = fgets(fid);
    if(tline(1) ~= -1) 
    a = str2num(tline);
    W(a(1)+1,a(2)+1) = a(3);
    W(a(2)+1,a(1)+1) = a(3);
    end
end
fclose(fid);
tic;
[LB,UB] = sdp(W);
elapsed = toc;





%We voldoen aan norm(B(i,:)) van i tot n


