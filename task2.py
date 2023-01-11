
print('This Software calculates your BMI (Body mass index) based on your height and weight. \nPlease enter with the following informations.')

# Verify if the user input is a float number
def isFloat(case):
    try:
        float(case)
        return True
    except:
        return False

userData=[]
# Name varification
while True:
    allowedChars = set('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ')
    case=input('Full Name: ') 
    verifyCase = set(case)
    if verifyCase.issubset(allowedChars):
        userData.append(case)
        break
    else:
        print('\nPlease enter with a string!')
# Gender verification
while True:
    case=input('Gender: ') 
    if case=='male' or case=='female' or case=='Male' or case=='Female':
        if(case=='male' or case=='Male'):
            case='Mr'
        else:
            case='Mrs'
        userData.append(case)
        break
    else:
        print('\nPlease enter with male or female!')

# Age verification
while True:
    case=input('Age: ')
    if case.isdigit():
        userData.append(int(case))
        break
    else:
        print('\nPlease enter with a valible age!')
# Weight verification
while True:
    case=input('Weight (kg): ')
    case=case.replace(',','.')
    if isFloat(case):
        userData.append(float(case))
        break
    else:
        print('\nPlease enter with a valible weight!')
# Height verification
while True:
    case=input('Height (m): ')
    case=case.replace(',','.')
    if isFloat(case):
        if '.' in case:
            userData.append(float(case))
            break
        else:
            userData.append(float(case)/100) 
            break
    else:
        print('\nPlease enter with a valible height!')

# calculate de BMI
bmi=(userData[3])/(userData[4]**2)

if userData[3]>=20:
    # categorzie the user BMI 
    if bmi<18.5:
        categorie='Underweight'
    elif bmi>=18.5 and bmi<=24.9:
        categorie='Appropriate weight'
    elif bmi>=25 and bmi<=29.9:
        categorie='Overweight'
    elif bmi>=30:
        categorie='Obesity'

# Women younger than 20
elif userData[3]<20 and userData[3]=='Mrs':
    if userData[3]>=10 and userData[3]<13:
        if bmi<15: categorie="Underweight"
        elif bmi>=15 and bmi<20 : categorie='Appropriate weight'
        elif bmi>=20: categorie='Overweight'
    if userData[3]>=13 and userData[3]<16:
        if bmi<16.40: categorie="Underweight"
        elif bmi>=16.4 and bmi<23 : categorie='Appropriate weight'
        elif bmi>=23: categorie='Overweight'
    if userData[3]>=16:
        if bmi<17: categorie="Underweight"
        elif bmi>=17 and bmi<24.5 : categorie='Appropriate weight'
        elif bmi>=24.5: categorie='Overweight'
    else:
        print('Sorry but your age is out of range')

# Men younger than 20
elif userData[3]<20 and userData[3]=='Mr':
    if userData[3]>=10 and userData[3]<13:
        if bmi<15.20: categorie="Underweight"
        elif bmi>=15.20 and bmi<19.50 : categorie='Appropriate weight'
        elif bmi>=19.50: categorie='Overweight'
    if userData[3]>=13 and userData[3]<16:
        if bmi<16.6: categorie="Underweight"
        elif bmi>=16.6 and bmi<20.5 : categorie='Appropriate weight'
        elif bmi>=20.5: categorie='Overweight'
    if userData[3]>=16:
        if bmi<18: categorie="Underweight"
        elif bmi>=18 and bmi<23 : categorie='Appropriate weight'
        elif bmi>=23: categorie='Overweight'
    else:
        print('Sorry but your age is out of range')

print('{fName}, your BMI is {bmi:.2f}, you are categorized as {cate}!'.format(fName=userData[0].upper(), bmi=bmi, cate=categorie))