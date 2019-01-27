function x = GaussJordan(a,b)

ab = [a,b]; %Joins the two matrices together

[R, C] = size(ab); %assigns R as the number of rows  
                   %and C as the number of columns

%-------------------BEGINNING of big FOR loop------------------------------
for j = 1:R
    
%-------------------BEGINNING of Pivoting Section--------------------------
    
    pvtemp = ab(j,j); %the pvtemp always starts as the (j,j) index
    kpvt = j;         %pivot row number
    
    %Now we look for the row with the largest pivot element at column j
    
    for k= j+1:R
       
        if ab(k,j)~=0 & abs(ab(k,j))> abs(pvtemp) 
            %if larger element exists in column jth
            pvtemp=ab(k,j) 
            
            kpvt = k; %pivot row is changed to be the k'th row.
        end
        
    end
%If a row with a larger pivot element is found to exist, switch rows
        
        if kpvt ~= j
%Basically, this means that if the above FOR loop makes any changes(the
%kpvt (pivot row) from 'j' to the new value of 'k') then we move the
%'k'th row to the top by switching it with the 'j'th row.        
            
            abTemp = ab(j,:); %the colon ':' at that position indicates 
                              %all column indexes on j'th row (whole row)
            ab(j,:) =ab(kpvt,:); 
            ab(kpvt,:)=abTemp;    
        
        end
        
%------------------END of Pivoting Section---------------------------------
    
    ab(j,:) = ab(j,:)/ab(j,j); %normalizes the j'th element of the j'th row
                               %by setting it to '1'.
    
    for i =1:R
        if i ~=j %this only applies if we're not treating the j'th row
            ab(i,j:C) = ab(i,j:C) - ab(i,j)*ab(j,j:C)
            
            %notice how the 'j:C' segment of the row gets smaller for
            %every 'j' when each time the bigger FOR loop runs
        end
    end          %this zeroes all elements in the column above and below
                 %the index (thanks to the IF condition)
  
end

%---------------------------END of big FOR loop----------------------------

x=ab(:,C) %assigns final column to x, which is simply the answer
    
    