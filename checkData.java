public class checkData {

    private String surname;
    private String name;
    private String patronymic;
    private String dateBirth;
    private long telephone;
    private char gender;
    private boolean correctData = true;

    public checkData(String enterString) {
        String[] enterStrings = enterString.split(" ");
        if (ProcessErrorCode(enterStrings)) {
            try {

                ParseData(enterStrings);
            } catch (RuntimeException e) {
                correctData = false;
                System.out.println(e.getMessage());
            }
        }
    }

    public String getSurname() {
        if (correctData) {
            return surname;
        }
        throw new RuntimeException("Invalid data.");
    }

    public String getName() {
        if (correctData) {
            return name;
        }
        throw new RuntimeException("Invalid data.");
    }

    public String getPatronymic() {
        if (correctData) {
            return patronymic;
        }
        throw new RuntimeException("Invalid data.");
    }

    public String getDateBirth() {
        if (correctData) {
            return dateBirth;
        }
        throw new RuntimeException("Invalid data.");
    }

    public long getTelephone() {
        if (correctData) {
            return telephone;
        }
        throw new RuntimeException("Invalid data.");
    }

    public char getGender() {
        if (correctData) {
            return gender;
        }
        throw new RuntimeException("Invalid data.");
    }

    public boolean getCorrect() {
        return correctData;
    }

    private int checkAmountData(String[] enterData) {
        if (enterData.length < 6) {
            return -1;
        } else if (enterData.length > 6) {
            return 1;
        }
        return 0;
    }

    private boolean ProcessErrorCode(String[] enterData) {
        int code = checkAmountData(enterData);
        if (code == -1) {
            System.out.println("Less data entered, than required." 
            + System.lineSeparator() + "You must enter surname, name, patronymic, date of birth, telephone, gender.");
            correctData = false;
            return false;
        } else if (code == 1) {
            System.out.println("More data entered, than required."
            + System.lineSeparator() + "You must enter surname, name, patronymic, date of birth, telephone, gender.");
            correctData = false;
            return false;
        }
        return true;
    }

    private void ParseData(String[] enterData) {
        for (String tempString : enterData) {
            if (Character.isDigit(tempString.charAt(0))) {
                DigitParseData(tempString);
            } else if (tempString.length() == 1) {
                if (tempString.equals("m") || tempString.equals("f")) {
                    gender = tempString.charAt(0);
                } else {
                    throw new RuntimeException(
                            "Invalid data, check the entered gender.");
                }
            } else if (Character.isAlphabetic(tempString.charAt(0))) {
                WordParseData(tempString);
            } else {
                throw new RuntimeException("The data you entered is incorrect.");
            }
        }

    }

    private void DigitParseData(String digitString) {
        if (digitString.indexOf('.') != -1) {
            DateParseData(digitString);
        } else {
            try {
                telephone = Long.parseLong(digitString);
            } catch (NumberFormatException e) {
                throw new RuntimeException("invalid phone number format.");
            }
        }
    }

    private void DateParseData(String dateString) {
        if (dateString.length() > 10 || dateString.codePointAt(2) != 46 || dateString.codePointAt(5) != 46) {
            throw new RuntimeException("invalid date of birth format.");
        }
        int day = 0;
        int month = 0;
        int year = 0;
        String[] temp = dateString.split("\\.");
        try {
            day = Integer.parseInt(temp[0]);
            month = Integer.parseInt(temp[1]);
            year = Integer.parseInt(temp[2]);
        } catch (NumberFormatException e) {
            System.out.println("invalid date of birth format.");
        }
        if (day > 0 && day < 32 && month > 0 && month < 13 && year > 1900 && year < 2024) {
            dateBirth = dateString;
        } else {
            throw new RuntimeException("invalid date of birth format.");
        }
    }

    public void WordParseData(String wordString) {
        for (int i = 0; i < wordString.length(); i++) {
            if (!Character.isAlphabetic(wordString.charAt(i))) {
                throw new RuntimeException("Error, check your surname, name, patronymic.");
            }
        }
        if (surname == null) {
            surname = wordString;
        } else if (name == null) {
            name = wordString;
        } else {
            patronymic = wordString;
        }
    }
}
