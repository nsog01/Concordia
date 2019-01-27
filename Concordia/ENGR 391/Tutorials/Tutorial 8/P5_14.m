clear, clc

kCH=5.92e2; kCC=15.8e2; mH=1.6605e-27; mC=12*mH;
c=3e8; 

M=zeros(4); %clean slate 4x4 matrix of zeroes
%Filling in the matrix M

M(1,1) =kCH/mH; M(1,2)=-M(1,1); %first row
M(2,1)= -kCH/mC; M(2,2)=(kCH + kCC)/mC; M(2,3)= -kCC/mC; %second row
M(3,2)=M(2,3); M(3,3)=M(2,2); M(3,4)=M(2,1); %third row
M(4,3)=M(1,2); M(4,4)=-M(4,3); %fourth row

[evectors values] = eig(M);

%evectors is a matrix where each column represents an eigen vector
%values is an nxn matrix in which the eigenvalues are stored in the diagonal
%of the matrix

eigenvalues=diag(values); %turns values matrix into a single column

temp = sqrt(eigenvalues); %refer to orignal matrix (eigenvalue = omega^2)
icount = 1; %for placing indexes, you'll see

%Looking for REAL eigenvalues and corresponding eigenvectors

for i=1:length(temp)
    if imag(temp(i))==0 %if imaginary part is zero (real number)
        number = temp(i)
        omega(icount) = number; %assigning number to vector of omegas
        eigenvectors(:,icount)=evectors(:,i); %assigning appropriate columns to eigenvectors
        icount = icount + 1;
    end
end

disp('The real eigenvalues are:')
omega
disp('The corresponding wavelengths (except for the zero eigenvalues are:')
for i =2:length(omega) %starts at 2 to skip first eigenvalue which is the zero eigenvalue
    lambda = 2*pi*c./omega;
end
lambda
disp('The eigenvectors corresponding to the eigenvalues are:')
eigenvectors
