function lines = readSdp(s, M);
%read graph into laplacian matrix L
%s = 'test2.txt';
fprintf('hi %s\n', s);
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

lines = sdp(W);





%We voldoen aan norm(B(i,:)) van i tot n


