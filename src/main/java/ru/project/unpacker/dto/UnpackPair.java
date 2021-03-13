package ru.project.unpacker.dto;

import java.util.Objects;

public class UnpackPair {
        int amountOfRepeat;
        String strokeToRepeat;

        public UnpackPair(int amountOfRepeat, String strokeToRepeat) {
            this.amountOfRepeat = amountOfRepeat;
            this.strokeToRepeat = strokeToRepeat;
        }

        public int getAmountOfRepeat() {
            return amountOfRepeat;
        }

        public void setAmountOfRepeat(int amountOfRepeat) {
            this.amountOfRepeat = amountOfRepeat;
        }

        public String getStrokeToRepeat() {
            return strokeToRepeat;
        }

        public void setStrokeToRepeat(String strokeToRepeat) {
            this.strokeToRepeat = strokeToRepeat;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UnpackPair that = (UnpackPair) o;
            return amountOfRepeat == that.amountOfRepeat &&
                    Objects.equals(strokeToRepeat, that.strokeToRepeat);
        }

        @Override
        public int hashCode() {
            return Objects.hash(amountOfRepeat, strokeToRepeat);
        }

    @Override
    public String toString() {
        return amountOfRepeat +
                "[" + strokeToRepeat + "]";
    }
}
